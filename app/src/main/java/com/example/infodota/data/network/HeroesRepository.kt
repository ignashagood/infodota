package com.example.infodota.data.network

import com.example.infodota.data.models.HeroNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class HeroesRepository @Inject constructor(private val api: OpenDotaApi) {
    suspend fun getAllHeroes(): Flow<Response<List<HeroNetworkModel>>> {
        return flow {
            emit(api.getAllHeroes())
        }.flowOn(Dispatchers.IO)
    }
}
