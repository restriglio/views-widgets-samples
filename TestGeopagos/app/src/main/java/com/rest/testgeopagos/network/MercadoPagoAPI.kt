package com.rest.testgeopagos.network

import androidx.lifecycle.LiveData
import com.rest.testgeopagos.model.CardIssuer
import com.rest.testgeopagos.model.Installment
import com.rest.testgeopagos.model.PaymentMethod
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  MercadoPagoAPI {

    @GET("payment_methods/")
    fun getPaymentMethodsAsync(@Query("public_key") key: String): Call<List<PaymentMethod>>

    @GET("payment_methods/card_issuers/")
    fun getCardIssuer(@Query("public_key") key: String,
                     @Query("payment_method_id") method: String ): Call<List<CardIssuer>>

    @GET("payment_methods/installments/")
    fun getInstallments(@Query("public_key") key: String,
                        @Query("amount") amount: String,
                        @Query("payment_method_id") payment_method_id: String,
                        @Query("issuer.id") issuer: String): Call<List<Installment>>
}