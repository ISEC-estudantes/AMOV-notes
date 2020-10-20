package pt.isec.a21280302.amov2020_2021

import android.app.Application
import android.util.Log

class MyApp : Application() {
    companion object {
        var conta = 0
        fun getContador(): Int {
            return conta++
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "MYAPP:onCreate: ${MyApp.getContador()}")
    }
}