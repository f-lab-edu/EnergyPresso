package com.eins.domain.repository.network

import kotlinx.coroutines.flow.Flow
import org.json.JSONObject


interface MqttBaseRepository {
    suspend fun subscribe(topic: String): Flow<JSONObject>
    suspend fun publish(topic: String, data: JSONObject)
}