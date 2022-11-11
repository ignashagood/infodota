package com.example.infodota.data.network

import com.example.infodota.data.models.TeamNetworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class TeamsRepository @Inject constructor(private val api: OpenDotaApi) {
    suspend fun getAllTeams(): Flow<Response<List<TeamNetworkModel>>> {
        return flow {
            emit(api.getAllTeams())
        }.flowOn(Dispatchers.IO)
    }
}