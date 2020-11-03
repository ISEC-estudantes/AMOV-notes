package com.github.onikenx.rascunhoamov

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_area_desenho.*

class AreaDesenhoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_desenho)

        val strTitulo = intent.getStringExtra("titulo") ?: "sem nome"
        val red = intent.getIntExtra("red", 255)
        val green = intent.getIntExtra("green", 255)
        val blue = intent.getIntExtra("blue", 255)

        supportActionBar?.title = strTitulo
        frAreaDesenho.addView(AreaDesenho(this, red, green, blue))


        // Legacy call
        //findViewById<View>(android.R.id.content).setBackgroundColor(Color.rgb(red, green, blue))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gravar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuGravar){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

