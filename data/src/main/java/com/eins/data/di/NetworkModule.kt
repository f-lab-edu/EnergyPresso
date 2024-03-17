package com.eins.data.di

import android.content.Context
import com.eins.data.repository.network.MqttBaseRepositoryImpl
import com.eins.data.util.AWSIoTManagerHandler
import com.eins.domain.repository.network.MqttBaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMqttModule(
        @ApplicationContext context: Context
    ): AWSIoTManagerHandler = AWSIoTManagerHandler(context)

    @Provides
    fun provideMqttBaseRepository(
        awsIoTManagerHandler: AWSIoTManagerHandler
    ): MqttBaseRepository = MqttBaseRepositoryImpl(awsIoTManagerHandler)
}