package com.example.infodota.data.network

import com.example.infodota.data.models.ProPlayerNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class PlayersRepository @Inject constructor(private val api: OpenDotaApi) {
    suspend fun getAllPlayers(): Flow<Response<List<ProPlayerNetworkModel>>> {
        return flow {
            emit(api.getAllPlayers())
        }.flowOn(Dispatchers.IO)
    }
}
