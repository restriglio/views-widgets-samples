package com.rest.testgeopagos.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectThis()
        super.onCreate(savedInstanceState)
    }

    abstract fun injectThis()

    protected fun showError(container : View, error : String) {
        Snackbar.make(
            container,
            error,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}