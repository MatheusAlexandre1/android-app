package com.bluedot.core.exception

import com.bluedot.core.exception.Error

interface ErrorHandler {

    fun getError(throwable: Throwable): Error
}