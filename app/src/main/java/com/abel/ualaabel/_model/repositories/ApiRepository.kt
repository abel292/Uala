package com.abel.ualaabel._model.repositories

import android.util.Log
import com.abel.ualaabel._model.remote.Pojo
import com.abel.ualaabel._model.remote.ryck_y_morti.ResultApi
import com.abel.ualaabel.service.remote.base.BaseRemoteRepository
import com.abel.ualaabel.service.remote.base.OnResponse
import com.abel.ualaabel.service.remote.base.RemoteErrorEmitter
import com.abel.ualaabel.service.remote.networking.PlanesApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiRepository() :
    BaseRemoteRepository() {

    suspend fun getCapitulos(
        remoteErrorEmitter: RemoteErrorEmitter,
        onResponse: OnResponse<ResultApi>
    ) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PlanesApi::class.java)
        val result = getResult { service.getAllCharacters() }

        onResponse.onResponse(OnResponse.ResponseType.OK, result, null)
    }


}