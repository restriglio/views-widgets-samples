package com.rest.testgeopagos.model

import com.google.gson.annotations.SerializedName

data class Installment(
    @SerializedName("payment_method_id")
    val paymentMethodId: String,
    @SerializedName("payment_type_id")
    val paymentTypeId: String,
    @SerializedName("payer_costs")
    val payerCosts: List<PayerCosts>


) {
    data class PayerCosts(
        @SerializedName("installments")
        val installments: String,
        @SerializedName("discount_rate")
        val discountRate: String,
        @SerializedName("labels")
        val labels: ArrayList<String>,
        @SerializedName("recommended_message")
        val recommendedMessage: String,
        @SerializedName("installment_amount")
        val installmentAmount: String,
        @SerializedName("total_amount")
        val totalAmount: String
    )
}