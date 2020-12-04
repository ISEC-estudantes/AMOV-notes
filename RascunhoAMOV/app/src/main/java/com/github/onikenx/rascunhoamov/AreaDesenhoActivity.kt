package com.github.onikenx.rascunhoamov

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AreaDesenhoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_desenho)

        val strTitulo = intent.getStringExtra("titulo")?: "sem nome"
        val red = intent.getIntExtra("red", 255)
        val green = intent.getIntExtra("green", 255)
        val blue = intent.getIntExtra("blue", 255)

        supportActionBar?.title = strTitulo
        findViewById<View>(android.R.id.content).setBackgroundColor(Color.rgb(red, green, blue))
    }
}

