package com.rest.testgeopagos.viewmodel

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.rest.testgeopagos.utils.SharedPreferencesManager
import java.text.DecimalFormat


class AmountInputViewModel : ViewModel() {

    fun nextStep(context: Context, amount : String) {
        SharedPreferencesManager.with(context).saveAmount(amount)
    }



}