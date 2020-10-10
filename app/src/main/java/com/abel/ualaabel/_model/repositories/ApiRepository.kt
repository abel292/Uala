package com.abel.ualaabel._model.repositories

import com.abel.ualaabel._model.remote.meals.ResultApi
import com.abel.ualaabel._model.remote.ryck_y_morti.ResultApiMorty
import com.abel.ualaabel.service.remote.base.BaseRemoteRepository
import com.abel.ualaabel.service.remote.base.OnResponse
import com.abel.ualaabel.service.remote.base.RemoteErrorEmitter
import com.abel.ualaabel.service.remote.networking.MealsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiRepository() :
    BaseRemoteRepository() {

    suspend fun getMeal(
        txtSeached: String,
        onResponse: OnResponse<ResultApi>
    ) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealsApi::class.java)
        val result = getResult { service.getMeals(txtSeached) }

        onResponse.onResponse(OnResponse.ResponseType.OK, result, null)
    }


    suspend fun getMealRandom(
        onResponse: OnResponse<ResultApi>
    ) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MealsApi::class.java)
        val result = getResult { service.getMealRandom() }

        onResponse.onResponse(OnResponse.ResponseType.OK, result, null)
    }


}