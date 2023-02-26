package com.weatherapp.core_network.exception

import java.io.IOException

class NetworkException(e: IOException) : IOException(e)

class NetworkHttpException(val code: Int, message: String) : Exception(message)