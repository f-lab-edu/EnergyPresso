package com.eins.data.util

import android.content.Context
import android.service.credentials.CredentialProviderService
import android.util.Log
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobileconnectors.iot.AWSIotKeystoreHelper
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.cognitoidentity.model.CognitoIdentityProvider
import com.amazonaws.services.iot.AWSIotClient
import com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest
import com.amazonaws.services.iot.model.CreateKeysAndCertificateRequest
import com.eins.data.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.json.JSONObject
import java.security.KeyStore
import java.util.UUID

class AWSIoTManagerHandler(
    context: Context,
    endpoint: String = BuildConfig.CUSTOMER_SPECIFIC_ENDPOINT,
): AWSIotMqttClientStatusCallback, AWSIotMqttNewMessageCallback {
    private val TAG = "AWSIoTManagerHandler"

    private val instanceId: String by lazy {
        "1234"
    }

    private val awsIotMqttManager = AWSIotMqttManager(instanceId, endpoint)

    private val keystorePath = context.filesDir.path
    private val keystoreName = BuildConfig.KEYSTORE_NAME
    private val keystorePassword = BuildConfig.KEYSTORE_PASSWORD
    private val certificateId = BuildConfig.CERTIFICATE_ID
    private val region: Region = Region.getRegion(Regions.AP_NORTHEAST_2)

    private val mqttFlowMap: MutableMap<String, MutableSharedFlow<JSONObject>> = mutableMapOf()

    private val _mqttStatus = MutableStateFlow<AWSIotMqttClientStatus?>(null)
    val mqttStatus: StateFlow<AWSIotMqttClientStatus?> = _mqttStatus

    init {
        AWSMobileClient.getInstance().getAWSCredentials(object : Callback<AWSCredentials>{
            override fun onResult(result: AWSCredentials?) {
                Log.d(TAG, """
                    getAWSCredentials success
                    awsSecretKey = ${result?.awsSecretKey}
                    awsAccessKeyId = ${result?.awsAccessKeyId}
                """.trimIndent())
            }

            override fun onError(e: java.lang.Exception?) {
                e?.printStackTrace()
            }
        })

        AWSMobileClient.getInstance().initialize(context, object : Callback<UserStateDetails> {
            override fun onResult(result: UserStateDetails?) {
                Log.d(TAG, """
                    AWSMobileClient init result = ${result.toString()}
                """.trimIndent())
                connect()
            }

            override fun onError(e: java.lang.Exception?) {
                Log.d(TAG, """
                    AWSMobileClient init error = ${e.toString()}
                """.trimIndent())
                e?.printStackTrace()
            }
        })
    }

    suspend fun subscribe(topic: String): Flow<JSONObject> {
        runCatching {
            withTimeout(3000){
                if(mqttStatus.value == AWSIotMqttClientStatus.ConnectionLost){
                    connect()
                }
                mqttStatus.collect{ status ->
                    if(status == AWSIotMqttClientStatus.Connected){
                        this.cancel()
                    }
                }
            }
        }

        if(mqttStatus.value == AWSIotMqttClientStatus.Connected){
            awsIotMqttManager.subscribeToTopic(topic, AWSIotMqttQos.QOS0, this)
        }

        return if(mqttFlowMap[topic] == null){
            val flow = MutableSharedFlow<JSONObject>(0, 1)
            mqttFlowMap[topic] = flow
            flow
        }else{
            mqttFlowMap[topic] as Flow<JSONObject>
        }
    }

    suspend fun publish(topic: String, message: JSONObject){
        runCatching {
            withTimeout(3000){
                if(mqttStatus.value == AWSIotMqttClientStatus.ConnectionLost){
                    connect()
                }
                mqttStatus.collect{ status ->
                    if(status == AWSIotMqttClientStatus.Connected){
                        awsIotMqttManager.publishData(message.toString().toByteArray(), topic, AWSIotMqttQos.QOS0)
                        this.cancel()
                    }
                }
            }
        }
    }

    fun connect() {
        val resultKey = try {
            val awsIoTClient = AWSIotClient(AWSMobileClient.getInstance())
            awsIoTClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2))

            AWSIotKeystoreHelper.isKeystorePresent(keystorePath, keystoreName)

            AWSIotKeystoreHelper.keystoreContainsAlias(
                certificateId, keystorePath,
                keystoreName, keystorePassword
            )

            AWSIotKeystoreHelper.getIotKeystore(
                certificateId,
                keystorePath, keystoreName, keystorePassword
            )
        }catch (e: Exception){
            createKeyStore()
        }

        awsIotMqttManager.connect(resultKey, this)
    }

    fun createKeyStore(): KeyStore {
        val awsIoTClient = AWSIotClient(AWSMobileClient.getInstance())
        awsIoTClient.setRegion(region)
        val request = CreateKeysAndCertificateRequest()
        request.setAsActive = true
        val result = awsIoTClient.createKeysAndCertificate(request)

        AWSIotKeystoreHelper
            .saveCertificateAndPrivateKey(
                certificateId,
                result.certificatePem,
                result.keyPair.privateKey,
                keystorePath,
                keystoreName,
                keystorePassword
            )


        val policyRequest = AttachPrincipalPolicyRequest()
        policyRequest.policyName = BuildConfig.AWS_IOT_POLICY_NAME
        policyRequest.principal = result.certificateArn

        awsIoTClient.attachPrincipalPolicy(policyRequest)

        return AWSIotKeystoreHelper.getIotKeystore(certificateId,
            keystorePath, keystoreName, keystorePassword)
    }

    override fun onStatusChanged(
        status: AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus?,
        throwable: Throwable?
    ) {
        Log.d("AWSIoTManagerHandler", """
            status = $status
        """.trimIndent())

        _mqttStatus.tryEmit(status)
    }

    override fun onMessageArrived(topic: String?, data: ByteArray?) {
        if(topic != null && data != null){
            val message = JSONObject(String(data, Charsets.UTF_8))
            mqttFlowMap[topic]?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    it.tryEmit(message)
                }
            } ?: kotlin.run {
                Log.d(TAG, """
                    mqttFlowMap not contains $topic
                """.trimIndent())
            }
        }
    }
}