package com.swapna.cakelistapp.data.repository

import com.swapna.cakelistapp.data.api.NetworkService
import com.swapna.cakelistapp.data.model.CakeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CakeRepository @Inject constructor(private val networkService: NetworkService) {

    fun cakeList() : Flow<List<CakeList>>{
        return flow {
            emit(networkService.getCakeList().distinctBy { it.title }.sortedBy { it.title })
        }
    }
}