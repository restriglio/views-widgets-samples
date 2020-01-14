package com.rest.testgeopagos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rest.testgeopagos.model.Installment
import com.rest.testgeopagos.model.MercadoPagoRepository
import com.rest.testgeopagos.network.ApiResponse
import javax.inject.Inject

class InstallmentsViewModel @Inject constructor(
    private val repository: MercadoPagoRepository
) : ViewModel() {

    fun requestInstallments( amount: String,
                             paymentMethodId: String,
                             issuer: String
    ) {
        repository.requestInstallments(amount, paymentMethodId, issuer)
    }

    fun getInstallments(): MutableLiveData<ApiResponse<List<Installment>>> {
        return repository.installments
    }

}