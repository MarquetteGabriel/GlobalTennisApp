/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.api

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class ApiService (context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private lateinit var apiModule: PyObject

    override fun doWork(): Result {
        if(!Python.isStarted()) {
            Python.start(AndroidPlatform(applicationContext))
        }

        apiModule = Python.getInstance().getModule("main")
        apiModule.callAttr("main")
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        val runtime = Runtime.getRuntime()
        val command = "pkill -f 'main.py 2607'"
        val process = runtime.exec(command)
        process.waitFor()
    }

    companion object {
        const val WORK_NAME = "ApiService"
    }
}