package com.rest.testgeopagos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rest.testgeopagos.model.CardIssuer
import com.rest.testgeopagos.model.MercadoPagoRepository
import com.rest.testgeopagos.network.ApiResponse
import javax.inject.Inject

class BankListViewModel  @Inject constructor(
    private val repository: MercadoPagoRepository
) : ViewModel() {

    fun requestBankList(paymentId : String) {
        repository.requestCardIssuer(paymentId)
    }

    fun getBankList() : MutableLiveData<ApiResponse<List<CardIssuer>>> {
        return repository.cardIssuer
    }
}