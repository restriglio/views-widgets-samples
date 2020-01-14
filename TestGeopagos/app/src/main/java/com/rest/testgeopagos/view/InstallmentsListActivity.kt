package com.rest.testgeopagos.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rest.testgeopagos.TestGeoPagosApp
import com.rest.testgeopagos.databinding.ActivityInstallmentListBinding
import com.rest.testgeopagos.model.Installment
import com.rest.testgeopagos.utils.SharedPreferencesManager
import com.rest.testgeopagos.view.adapters.InstallmentsAdapter
import com.rest.testgeopagos.viewmodel.InstallmentsViewModel
import javax.inject.Inject

class InstallmentsListActivity : BaseActivity() {

    private lateinit var binding: ActivityInstallmentListBinding
    lateinit var viewModel: InstallmentsViewModel

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstallmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[InstallmentsViewModel::class.java]

        val sharedPref = SharedPreferencesManager.with(this)
        viewModel.requestInstallments(sharedPref.getAmount(), sharedPref.getPaymentId(), sharedPref.getIssuerId())
        viewModel.getInstallments().observe(this, Observer { result ->
            binding.progress.visibility = View.GONE
            if(!result.isSuccessful || result.response.isEmpty()) {
                showError(binding.root, "Ha ocurrido un error")
                return@Observer
            }
            configureInstallments(result.response)
        })
    }

    private fun configureInstallments(response: List<Installment>) {
        val banksAdapter = InstallmentsAdapter(this, response[0].payerCosts, this::onInstallmentCLicked)
        binding.rvInstallments.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = banksAdapter
        }
    }

    private fun onInstallmentCLicked() {
        val intent = Intent(this, PaymentConfirmedActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun injectThis() {
        (applicationContext as TestGeoPagosApp).appComponent.inject(this)
    }
}