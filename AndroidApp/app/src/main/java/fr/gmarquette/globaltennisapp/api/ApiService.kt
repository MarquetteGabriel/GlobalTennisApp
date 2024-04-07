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

    companion object {
        const val WORK_NAME = "ApiService"
    }
}