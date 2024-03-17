package com.eins.data.repository.network

import com.eins.data.util.AWSIoTManagerHandler
import com.eins.domain.repository.network.MqttBaseRepository
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

class MqttBaseRepositoryImpl(
    private val awsIoTManagerHandler: AWSIoTManagerHandler
): MqttBaseRepository {
    override suspend fun subscribe(topic: String): Flow<JSONObject> {
        return awsIoTManagerHandler.subscribe(topic)
    }

    override suspend fun publish(topic: String, data: JSONObject) {
        awsIoTManagerHandler.publish(topic, data)
    }
}