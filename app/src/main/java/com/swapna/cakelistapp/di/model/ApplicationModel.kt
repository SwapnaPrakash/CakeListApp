package com.swapna.cakelistapp.di.model

import com.swapna.cakelistapp.data.api.NetworkService
import com.swapna.cakelistapp.di.BaseUrl
import com.swapna.cakelistapp.utiles.DefaultDispatcherProvider
import com.swapna.cakelistapp.utiles.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModel {

    @Provides
    @BaseUrl
    fun provideBaseUrl():String = "https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory():GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(@BaseUrl baseUrl:String, gsonConverterFactory: GsonConverterFactory):NetworkService{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build().create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider():DispatcherProvider = DefaultDispatcherProvider()
}