package com.rest.testgeopagos.model

import com.google.gson.annotations.SerializedName

data class CardIssuer(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("processing_mode")
    val processingMode: String,
    @SerializedName("merchant_account_id")
    val merchantAccountId: String
)