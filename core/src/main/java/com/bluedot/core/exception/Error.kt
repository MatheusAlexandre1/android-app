package com.bluedot.core.exception

sealed class Error : Throwable() {
    object ServerError : Error()
    abstract class FeatureError : Error()
}
