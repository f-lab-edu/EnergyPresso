package com.eins.domain.usecase.network

import com.eins.domain.repository.network.MqttBaseRepository
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject


/**
 *  단순 통신 테스트용입니다. 실제로는 이렇게 사용하지 않으려 합니다
 */
class MqttBaseUseCase (
    private val mqttBaseRepository: MqttBaseRepository
) {
    suspend fun subscribe(topic: String): Flow<JSONObject> = mqttBaseRepository.subscribe(topic)

    suspend fun publish(topic: String, message: JSONObject){
        mqttBaseRepository.publish(topic, message)
    }
}