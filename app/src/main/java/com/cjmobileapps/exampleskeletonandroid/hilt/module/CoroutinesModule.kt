package com.cjmobileapps.exampleskeletonandroid.hilt.module

import com.cjmobileapps.exampleskeletonandroid.util.coroutine.CoroutineDispatchers
import com.cjmobileapps.exampleskeletonandroid.util.coroutine.CoroutineDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesModule {

    @Singleton
    @Provides
    fun coroutinesDispatchers(): CoroutineDispatchers {
        return CoroutineDispatchersImpl
    }
}
