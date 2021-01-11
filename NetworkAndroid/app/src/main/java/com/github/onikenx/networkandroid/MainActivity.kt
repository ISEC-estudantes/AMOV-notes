package com.github.onikenx.networkandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.github.onikenx.networkandroid.NetUtils.getDataAsync
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var tvTexto : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTexto = findViewById(R.id.tvTexto)


        if (!NetUtils.verifyNetworkStateV2(this)) {
            Toast.makeText(this,"No network available",Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val xxx = JSONObject()
        xxx.put("name", "lolnome")
        xxx.put("age", 40)
        val str = xxx.toString()
        val webContent : MutableLiveData<String?> = MutableLiveData()


        webContent.observe(this) {

            var str ="Informaçao recebida: \n"
            try{
                val obj = JSONObject(it)
                str += obj.toString(4)
                val objWind= obj.getJSONObject("wind")
                val objWind1 = obj["wind"] as JSONObject
                val speed = objWind.getDouble("speed")
                str += "\n\nVelocidade do vento: $speed\n"
            }catch(e : Exception){
                str += "Erro a processar JSON\n"
            }
            tvTexto.text = str
        }
        val strLink = "https://api.openweathermap.org/data/2.5/weather?q=Coimbra&appid=cce5a1d3924fee25efc9b20106b88017"
        NetUtils.getDataAsync(strLink, webContent)
//        getDataAsync("https://www.isec.pt",webContent)

    }

}