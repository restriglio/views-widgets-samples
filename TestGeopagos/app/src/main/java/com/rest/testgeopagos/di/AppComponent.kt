package com.rest.testgeopagos.di

import com.rest.testgeopagos.di.viewmodel.ViewModelModule
import com.rest.testgeopagos.view.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: InputAmountActivity)
    fun inject(activity: PaymentMethodsActivity)
    fun inject(activity: BankListActivity)
    fun inject(activity: InstallmentsListActivity)
    fun inject(activity: PaymentConfirmedActivity)
}