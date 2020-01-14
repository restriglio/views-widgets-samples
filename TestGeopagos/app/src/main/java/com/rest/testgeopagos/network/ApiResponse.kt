package com.rest.testgeopagos.network

data class ApiResponse<T>(
    val isSuccessful : Boolean,
    val response : T,
    val error : String
)