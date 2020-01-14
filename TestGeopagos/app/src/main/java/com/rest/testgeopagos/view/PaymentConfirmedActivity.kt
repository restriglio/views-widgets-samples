package com.rest.testgeopagos.view

import android.content.Intent
import android.os.Bundle
import com.rest.testgeopagos.R
import com.rest.testgeopagos.TestGeoPagosApp
import com.rest.testgeopagos.databinding.ActivityPaymentConfirmedBinding
import com.rest.testgeopagos.utils.SharedPreferencesManager

class PaymentConfirmedActivity : BaseActivity() {

    private lateinit var binding: ActivityPaymentConfirmedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.amountDetail.text = getString(R.string.money_prefix, SharedPreferencesManager.with(this).getAmount())
        binding.bankDetail.text = getString(R.string.confirmed_bank, SharedPreferencesManager.with(this).getIssuerName())
        binding.installmentDetail.text = SharedPreferencesManager.with(this).geInstallment()
        binding.paymentDetail.text = getString(R.string.confimerd_payment, SharedPreferencesManager.with(this).getPaymentDetail())

        binding.finishButton.setOnClickListener {
            val intent = Intent(this, InputAmountActivity::class.java)
            SharedPreferencesManager.with(this).clearPref()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun injectThis() {
        (applicationContext as TestGeoPagosApp).appComponent.inject(this)
    }
}