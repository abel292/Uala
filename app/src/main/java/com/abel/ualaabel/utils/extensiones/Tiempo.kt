package com.abel.ualaabel.utils.extensiones

import android.os.Handler

fun postDelayedWithTime(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}