package com.abel.ualaabel._view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.abel.ualaabel._model.remote.meals.Meal
import com.abel.ualaabel._model.remote.meals.ResultApi
import com.abel.ualaabel._model.repositories.ApiRepository
import com.abel.ualaabel.service.remote.base.ErrorType
import com.abel.ualaabel.service.remote.base.OnResponse
import com.abel.ualaabel.service.remote.base.RemoteErrorEmitter
import kotlinx.coroutines.*

class ApiViewModel(application: Application) :
    BaseViewModel(application), RemoteErrorEmitter {

    val list = MutableLiveData<List<Meal>>()
    val mealRandom = MutableLiveData<Meal>()
    private val repositoryPlanesEnel = ApiRepository()

    fun getPlanes(txtString: String) {
        GlobalScope.launch {
            repositoryPlanesEnel.getMeal(txtString, object : OnResponse<ResultApi> {
                override fun onResponse(
                    responseType: OnResponse.ResponseType,
                    object0: ResultApi?,
                    listEntity: List<ResultApi>?
                ) {
                    if (object0 != null) {
                        list.postValue(object0.meals)
                    }
                }

                override fun onError(code: Int, error: String?) {
                }

            })
        }
    }

    fun getMealRandom() {
        GlobalScope.launch {
            repositoryPlanesEnel.getMealRandom(object : OnResponse<ResultApi> {
                override fun onResponse(
                    responseType: OnResponse.ResponseType,
                    object0: ResultApi?,
                    listEntity: List<ResultApi>?
                ) {
                    if (object0 != null) {
                        if (!object0.meals.isNullOrEmpty()) {
                            mealRandom.postValue(object0.meals[0])
                        }
                    }
                }

                override fun onError(code: Int, error: String?) {
                }

            })
        }
    }

    override fun onError(msg: String) {

    }

    override fun onError(errorType: ErrorType) {
    }
}