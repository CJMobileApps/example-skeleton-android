package com.cjmobileapps.exampleskeletonandroid.util.coroutine

import kotlinx.coroutines.Dispatchers

object CoroutineDispatchersImpl : CoroutineDispatchers {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
}
