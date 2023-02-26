package com.weatherapp.core_network.model

import com.google.gson.annotations.SerializedName

class ErrorDto(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("errors")
    val errors: List<String>?
)