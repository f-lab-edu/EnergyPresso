package com.eins.energypresso.util

import android.content.Context
import android.util.Log
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobileconnectors.iot.AWSIotKeystoreHelper
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.iot.AWSIotClient
import com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest
import com.amazonaws.services.iot.model.CreateKeysAndCertificateRequest
import com.eins.energypresso.BuildConfig
import kotlinx.coroutines.delay
import java.security.KeyStore

class AWSIoTManagerHandler(
    private val context: Context,
    mqttClientId: String,
    endpoint: String = BuildConfig.CUSTOMER_SPECIFIC_ENDPOINT
): AWSIotMqttManager(
    mqttClientId, endpoint
) {
    suspend fun connect(statusCallback: AWSIotMqttClientStatusCallback) {
        val keystorePath = context.filesDir.path
        val keystoreName = BuildConfig.KEYSTORE_NAME
        val keystorePassword = BuildConfig.KEYSTORE_PASSWORD
        val certificateId = BuildConfig.CERTIFICATE_ID

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

        super.connect(resultKey, statusCallback)
    }

    suspend fun createKeyStore(): KeyStore {
        val region: Region = Region.getRegion(Regions.AP_NORTHEAST_2)

        val keystorePath = context.filesDir.path
        val keystoreName = BuildConfig.KEYSTORE_NAME
        val keystorePassword = BuildConfig.KEYSTORE_PASSWORD
        val certificateId = BuildConfig.CERTIFICATE_ID

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
}