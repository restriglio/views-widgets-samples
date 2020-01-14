package com.rest.testgeopagos.di

import com.rest.testgeopagos.model.MercadoPagoRepository
import com.rest.testgeopagos.network.MercadoPagoAPI
import dagger.Module
import dagger.Provides

@Module
class MercadoPagoModule {

    @Provides
    fun provideMercadoPAgoRepository(api : MercadoPagoAPI): MercadoPagoRepository {
        return MercadoPagoRepository(api)
    }

}