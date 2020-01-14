package com.rest.testgeopagos.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rest.testgeopagos.viewmodel.BankListViewModel
import com.rest.testgeopagos.viewmodel.InstallmentsViewModel
import com.rest.testgeopagos.viewmodel.PaymentMethodsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PaymentMethodsViewModel::class)
    abstract fun bindsPaymentMethodsViewModel(viewModel: PaymentMethodsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BankListViewModel::class)
    abstract fun bindBankListViewModel(viewModel: BankListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(InstallmentsViewModel::class)
    abstract fun bindInstallmentsViewModel(viewModel: InstallmentsViewModel): ViewModel
}
