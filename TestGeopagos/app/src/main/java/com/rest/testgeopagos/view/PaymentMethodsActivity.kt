package com.rest.testgeopagos.view

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rest.testgeopagos.TestGeoPagosApp
import com.rest.testgeopagos.databinding.ActivityPaymentMethodsBinding
import com.rest.testgeopagos.model.PaymentMethod
import com.rest.testgeopagos.utils.SharedPreferencesManager
import com.rest.testgeopagos.view.adapters.PaymentMethodsAdapter
import com.rest.testgeopagos.viewmodel.PaymentMethodsViewModel
import javax.inject.Inject

class PaymentMethodsActivity : BaseActivity() {


    lateinit var viewModel: PaymentMethodsViewModel
    private lateinit var binding: ActivityPaymentMethodsBinding

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[PaymentMethodsViewModel::class.java]

        viewModel.requestPaymentMethods()
        viewModel.getPaymentMethods().observe(this, Observer { result ->
            binding.progress.visibility = GONE
            if(!result.isSuccessful || result.response.isEmpty()) {
                showError(binding.root, "Ha ocurrido un error")
                return@Observer
            }
            configurePaymentMethodsList(result.response)
        })
    }

    private fun configurePaymentMethodsList(list: List<PaymentMethod>) {
        val paymentsAdapter = PaymentMethodsAdapter(list, this::onClickMethod)
        binding.rvPaymentMethods.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = paymentsAdapter

        }
    }

    private fun onClickMethod(paymentId : String, paymentDetail : String) {
        val intent = Intent(this, BankListActivity::class.java)
        SharedPreferencesManager.with(this).savePaymentId(paymentId)
        SharedPreferencesManager.with(this).savePaymentDetail(paymentDetail)
        startActivity(intent)
    }

    override fun injectThis() {
        (applicationContext as TestGeoPagosApp).appComponent.inject(this)
    }

}