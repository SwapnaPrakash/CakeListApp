package com.swapna.cakelistapp.data.api

import com.swapna.cakelistapp.data.model.CakeList
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("waracle_cake-android-client")
    suspend fun getCakeList() : List<CakeList>

}