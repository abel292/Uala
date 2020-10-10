package com.abel.ualaabel._view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.abel.ualaabel._model.remote.ryck_y_morti.Personaje
import com.abel.ualaabel._model.remote.ryck_y_morti.ResultApi
import com.abel.ualaabel._model.repositories.ApiRepository
import com.abel.ualaabel.service.remote.base.ErrorType
import com.abel.ualaabel.service.remote.base.OnResponse
import com.abel.ualaabel.service.remote.base.RemoteErrorEmitter
import kotlinx.coroutines.*

class ApiViewModel(application: Application) :
    BaseViewModel(application), RemoteErrorEmitter {

    val list = MutableLiveData<List<Personaje>>()
    private val repositoryPlanesEnel = ApiRepository()

    fun getPlanes() {
        GlobalScope.launch {
            repositoryPlanesEnel.getCapitulos(this@ApiViewModel, object : OnResponse<ResultApi> {
                override fun onResponse(
                    responseType: OnResponse.ResponseType,
                    object0: ResultApi?,
                    listEntity: List<ResultApi>?
                ) {
                    if (object0 != null) {
                        list.postValue(object0.personajes)
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