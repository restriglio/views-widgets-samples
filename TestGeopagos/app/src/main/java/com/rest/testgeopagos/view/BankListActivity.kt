package com.rest.testgeopagos.view

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rest.testgeopagos.TestGeoPagosApp
import com.rest.testgeopagos.databinding.ActivityBankListBinding
import com.rest.testgeopagos.model.CardIssuer
import com.rest.testgeopagos.utils.SharedPreferencesManager
import com.rest.testgeopagos.view.adapters.BanksAdapter
import com.rest.testgeopagos.viewmodel.BankListViewModel
import javax.inject.Inject

class BankListActivity : BaseActivity() {

    private lateinit var binding: ActivityBankListBinding
    lateinit var viewModel: BankListViewModel

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[BankListViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestBankList(SharedPreferencesManager.with(this).getPaymentId())
        viewModel.getBankList().observe(this, Observer { result ->
            binding.progress.visibility = GONE
            if(!result.isSuccessful || result.response.isEmpty()) {
                showError(binding.root, "Ha ocurrido un error")
                return@Observer
            }
            configureBankList(result.response)
        })
    }

    private fun configureBankList(banks: List<CardIssuer>) {
        val banksAdapter = BanksAdapter(banks, this::onBankClicked)
        binding.rvBanks.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = banksAdapter
        }
    }

    private fun onBankClicked(bankId : String, bankName : String) {
        val intent = Intent(this, InstallmentsListActivity::class.java)
        SharedPreferencesManager.with(this).saveIssuerId(bankId)
        SharedPreferencesManager.with(this).saveIssuerName(bankName)
        startActivity(intent)
    }

    override fun injectThis() {
        (applicationContext as TestGeoPagosApp).appComponent.inject(this)
    }
}