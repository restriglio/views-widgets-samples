package com.rest.testgeopagos.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.rest.testgeopagos.TestGeoPagosApp
import com.rest.testgeopagos.databinding.ActivityAmountInputBinding
import com.rest.testgeopagos.databinding.ActivityPaymentMethodsBinding
import com.rest.testgeopagos.utils.SharedPreferencesManager
import java.text.DecimalFormat
import java.text.NumberFormat

class InputAmountActivity : BaseActivity() {

    private lateinit var binding : ActivityAmountInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmountInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.inputAmount.addTextChangedListener(CurrencyTextWatcher(binding))
        binding.continueButton.setOnClickListener {
            SharedPreferencesManager.with(this).saveAmount(binding.inputAmount.text.toString())
            startActivity(Intent(this, PaymentMethodsActivity::class.java))
        }
    }


    internal class CurrencyTextWatcher(private val binding: ActivityAmountInputBinding) : TextWatcher {
        private var mEditing = false

        @Synchronized
        override fun afterTextChanged(s: Editable) {
            if (!mEditing) {
                mEditing = true
                val digits = s.toString().replace("\\D".toRegex(), "")
                val nf: NumberFormat = DecimalFormat.getCurrencyInstance()
                try {
                    val formatted: String = nf.format(digits.toDouble() / 100)
                    s.replace(0, s.length, formatted)
                } catch (nfe: NumberFormatException) {
                    s.clear()
                }
                mEditing = false

                binding.continueButton.isEnabled = s.toString().toDouble() > 0
            }
        }

        override fun beforeTextChanged(
            s: CharSequence,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence,
            start: Int,
            before: Int,
            count: Int
        ) {
        }

    }

    override fun injectThis() {
        (applicationContext as TestGeoPagosApp).appComponent.inject(this)
    }
}
