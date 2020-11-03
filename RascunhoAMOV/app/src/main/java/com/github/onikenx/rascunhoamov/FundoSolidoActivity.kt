package com.github.onikenx.rascunhoamov

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_fundo_solido.*

class FundoSolidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fundo_solido)
        seekRed.setOnSeekBarChangeListener(procSeekBars)
        seekGreen.setOnSeekBarChangeListener(procSeekBars)
        seekBlue.setOnSeekBarChangeListener(procSeekBars)
        seekRed.progress = 255
        seekGreen.progress = 255
        seekBlue.progress = 255
    }

    val procSeekBars = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            frPreview.setBackgroundColor(
                Color.rgb(
                    seekRed.progress,
                    seekGreen.progress,
                    seekBlue.progress
                )
            )

        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_criar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuCriar) {
            if (edTitulo.text.length < 1) {
                Toast.makeText(this, R.string.msg_erro_titulo, Toast.LENGTH_SHORT).show()
                Snackbar.make(edTitulo, R.string.msg_erro_titulo, Snackbar.LENGTH_LONG).show()
                return true
            }
            val intent = Intent(this, AreaDesenhoActivity::class.java)
            intent.putExtra("titulo", edTitulo.text.toString())
            intent.putExtra("red", seekRed.progress)
            intent.putExtra("green", seekGreen.progress)
            intent.putExtra("blue", seekBlue.progress)
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
