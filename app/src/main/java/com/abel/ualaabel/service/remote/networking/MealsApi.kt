package com.abel.ualaabel.service.remote.networking


import com.abel.ualaabel._model.remote.meals.ResultApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("api/json/v1/1/search.php")
    suspend fun getMeals(@Query("s") search: String?) : Response<ResultApi>


    @GET("api/json/v1/1/random.php")
    suspend fun getMealRandom() : Response<ResultApi>



}