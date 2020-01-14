package com.rest.testgeopagos.model

import androidx.lifecycle.MutableLiveData
import com.rest.testgeopagos.BuildConfig.API_KEY
import com.rest.testgeopagos.network.ApiResponse
import com.rest.testgeopagos.network.MercadoPagoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MercadoPagoRepository @Inject constructor(
    private val api: MercadoPagoAPI
) {

    var payments = MutableLiveData<ApiResponse<List<PaymentMethod>>>()
    var cardIssuer = MutableLiveData<ApiResponse<List<CardIssuer>>>()
    var installments = MutableLiveData<ApiResponse<List<Installment>>>()


    fun requestPaymentMethodsAsync() {
        api.getPaymentMethodsAsync(API_KEY).enqueue(object :
            Callback<List<PaymentMethod>> {
            override fun onResponse(
                call: Call<List<PaymentMethod>>,
                response: Response<List<PaymentMethod>>
            ) {

                payments.postValue(
                    ApiResponse(
                        response.isSuccessful,
                        response.body() ?: ArrayList(),
                        "error"
                    )
                )
            }

            override fun onFailure(call: Call<List<PaymentMethod>>, t: Throwable) {
                payments.postValue(
                    ApiResponse(
                        false,
                        ArrayList(),
                        "error"
                    )
                )
            }
        })
    }

    fun requestCardIssuer(paymentMethodId: String) {
        api.getCardIssuer(API_KEY, paymentMethodId).enqueue(object :
            Callback<List<CardIssuer>> {
            override fun onResponse(
                call: Call<List<CardIssuer>>,
                response: Response<List<CardIssuer>>
            ) {
                cardIssuer.postValue(
                    ApiResponse(
                        response.isSuccessful,
                        response.body() ?: ArrayList(),
                        "error"
                    )
                )
            }

            override fun onFailure(call: Call<List<CardIssuer>>, t: Throwable) {
                cardIssuer.postValue(
                    ApiResponse(
                        false,
                        ArrayList(),
                        "error"
                    )
                )
            }
        })
    }

    fun requestInstallments(
        amount: String,
        paymentMethodId: String,
        issuer: String
    ) {
        api.getInstallments(API_KEY, amount, paymentMethodId, issuer).enqueue(object :
            Callback<List<Installment>> {
            override fun onResponse(
                call: Call<List<Installment>>,
                response: Response<List<Installment>>
            ) {
                installments.postValue(
                    ApiResponse(
                        response.isSuccessful,
                        response.body() ?: ArrayList(),
                        "error"
                    )
                )
            }

            override fun onFailure(call: Call<List<Installment>>, t: Throwable) {
                installments.postValue(
                    ApiResponse(
                        false,
                        ArrayList(),
                        "error"
                    )
                )
            }
        })
    }
}