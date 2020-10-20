package pt.isec.a21280302.amov_aula2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Principal : Activity(),View.OnClickListener {
    lateinit var tvTexto1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.principal)

        tvTexto1 = findViewById(R.id.tvTexto1)
        tvTexto1.text = "2020.10.20"
        val btn1 : Button = findViewById(R.id.btn1)
        btn1.setOnClickListener(this)
        val btn2 : Button = findViewById(R.id.btn2)
        btn2.setOnClickListener(ProcBtn2())
        val btn3 : Button = findViewById(R.id.btn3)
        findViewById<Button>(R.id.btn3).setOnClickListener(procBtn3)
        val btn4 : Button = findViewById(R.id.btn4)
        findViewById<Button>(R.id.btn4).setOnClickListener{
            tvTexto1.text = "Botao 4"
            Toast.makeText(this, "Mensagem", Toast.LENGTH_LONG).show()
        }

    }

    fun onBotao5(v : View){
        tvTexto1.text = "Buttao 5"
    }

    override fun onClick(v: View?){
        tvTexto1.text = "Botao 1"
    }

    inner class ProcBtn2 : View.OnClickListener{
        override fun onClick(v: View?){
            this@Principal.tvTexto1.text = "Botao 2"
        }


    }
    val procBtn3 = View.OnClickListener { tvTexto1.text = "Botao 3" }
}