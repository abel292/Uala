package com.abel.ualaabel.service.remote.base

interface OnResponse<T> {
    enum class ResponseType {
        OK, BAD
    }

    fun onResponse(
        responseType: ResponseType,
        object0: T?,
        listEntity: List<T>?
    )

    fun onError(code: Int, error: String?)
}