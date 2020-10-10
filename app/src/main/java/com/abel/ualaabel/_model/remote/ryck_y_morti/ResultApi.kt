package com.abel.ualaabel._model.remote.ryck_y_morti

import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val personajes: List<Personaje>
)