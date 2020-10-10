package com.abel.ualaabel

import android.app.Application


class UalaApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: UalaApplication? = null

        fun applicationContext(): UalaApplication {
            return instance as UalaApplication
        }
    }
}