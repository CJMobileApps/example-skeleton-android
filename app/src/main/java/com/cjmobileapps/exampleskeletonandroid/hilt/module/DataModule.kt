package com.cjmobileapps.exampleskeletonandroid.hilt.module

import com.cjmobileapps.exampleskeletonandroid.data.ExampleSkeletonAndroidRepository
import com.cjmobileapps.exampleskeletonandroid.network.ExampleSkeletonAndroidApi
import com.cjmobileapps.exampleskeletonandroid.util.coroutine.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun mainRepository(
        exampleSkeletonAndroidApi: ExampleSkeletonAndroidApi,
        coroutineDispatchers: CoroutineDispatchers
    ): ExampleSkeletonAndroidRepository {
        return ExampleSkeletonAndroidRepository(
            exampleSkeletonAndroidApi = exampleSkeletonAndroidApi,
            coroutineDispatchers = coroutineDispatchers
        )
    }
}
