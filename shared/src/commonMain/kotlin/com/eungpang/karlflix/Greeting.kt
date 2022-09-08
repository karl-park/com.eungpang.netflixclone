package com.eungpang.karlflix

import kotlinx.coroutines.delay

class Greeting {
    @Throws(Exception::class)
    suspend fun greeting(): String {
        delay(100L)
        return "Hello, ${Platform().platform}!"
    }
}