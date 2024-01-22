package com.cjmobileapps.exampleskeletonandroid.util.coroutine

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {
    val io: CoroutineContext
    val main: CoroutineContext
}
