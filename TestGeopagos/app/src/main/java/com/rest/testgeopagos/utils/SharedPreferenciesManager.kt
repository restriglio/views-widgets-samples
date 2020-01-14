package com.rest.testgeopagos.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager @SuppressLint("CommitPrefEdits") constructor(context: Context) {

    companion object {
        private var singleton: SharedPreferencesManager? = null
        private lateinit var preferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        fun with(context: Context): SharedPreferencesManager {
            if (null == singleton)
                singleton = SharedPreferencesManager(context)
            return singleton as SharedPreferencesManager
        }
    }

    private val amountKey = "amountKey"
    private val paymentKey = "paymentKey"
    private val paymentDetailKey = "paymentDetailKey"
    private val issuerKey = "issuerKey"
    private val issuerNameKey = "issuerNameKey"
    private val installmentKey = "installmentKey"

    init {
        preferences = context.getSharedPreferences("TEST_GEO_PAGOS", Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    fun saveAmount(amount: String) {
        editor.putString(amountKey, amount).apply()
    }

    fun getAmount() : String {
        return preferences.getString(amountKey, "") ?: ""
    }

    private fun clearAmount() {
        editor.remove(amountKey)
    }

    fun savePaymentId(payment: String) {
        editor.putString(paymentKey, payment).apply()
    }

    fun getPaymentId() : String {
        return preferences.getString(paymentKey, "") ?: ""
    }

    fun savePaymentDetail(payment: String) {
        editor.putString(paymentDetailKey, payment).apply()
    }

    fun getPaymentDetail() : String {
        return preferences.getString(paymentDetailKey, "") ?: ""
    }

    private fun clearPayment() {
        editor.remove(paymentKey)
        editor.remove(paymentDetailKey)
    }

    fun saveIssuerId(issuer: String) {
        editor.putString(issuerKey, issuer).apply()
    }

    fun getIssuerId() : String {
        return preferences.getString(issuerKey, "") ?: ""
    }


    fun saveIssuerName(issuer: String) {
        editor.putString(issuerNameKey, issuer).apply()
    }

    fun getIssuerName() : String {
        return preferences.getString(issuerNameKey, "") ?: ""
    }

    private fun clearIssuer() {
        editor.remove(issuerKey)
        editor.remove(issuerNameKey)
    }

    fun saveInstallment(installment: String) {
        editor.putString(installmentKey, installment).apply()
    }

    fun geInstallment() : String {
        return preferences.getString(installmentKey, "") ?: ""
    }

    private fun clearInstallment() {
        editor.remove(installmentKey)
    }

    fun clearPref() {
        clearAmount()
        clearIssuer()
        clearPayment()
    }

}