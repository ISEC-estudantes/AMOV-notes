package pt.isec.a21280302.amov2020_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val TAG = "AMOV2020-2021"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "na criaçao: ")

    }
    override fun onStart(){
        super.onStart()
        Log.i(TAG, "no começo: ")
    }

    override fun onResume(){
        super.onResume()
        Log.i(TAG, "no resumo: ")
    }

    override fun onPause(){
        super.onPause()
        Log.i(TAG, "na pausa: ")
    }

    override fun onStop(){
        super.onStop()
        Log.i(TAG, "na paragem: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "na destruicao: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")

    }
}