package com.rest.testgeopagos

import android.app.Application
import com.rest.testgeopagos.di.DaggerAppComponent

class TestGeoPagosApp : Application() {

    val appComponent = DaggerAppComponent.create()

}