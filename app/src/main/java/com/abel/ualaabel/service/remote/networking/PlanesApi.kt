package com.abel.ualaabel.service.remote.networking

import com.abel.ualaabel._model.remote.ryck_y_morti.ResultApiMorty
import retrofit2.Response
import retrofit2.http.*

interface PlanesApi {
    @GET("character")
    suspend fun getAllCharacters() : Response<ResultApiMorty>
}