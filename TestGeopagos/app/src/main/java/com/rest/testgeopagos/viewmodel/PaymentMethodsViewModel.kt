package com.rest.testgeopagos.viewmodel

import androidx.lifecycle.*
import com.rest.testgeopagos.model.MercadoPagoRepository
import com.rest.testgeopagos.model.PaymentMethod
import com.rest.testgeopagos.network.ApiResponse
import javax.inject.Inject

class PaymentMethodsViewModel @Inject constructor(
    private val repository: MercadoPagoRepository
) : ViewModel() {


    fun requestPaymentMethods() {
        repository.requestPaymentMethodsAsync()
    }

    fun getPaymentMethods(): MutableLiveData<ApiResponse<List<PaymentMethod>>> {
        return repository.payments
    }

}