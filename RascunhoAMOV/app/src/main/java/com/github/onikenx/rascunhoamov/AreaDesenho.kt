package com.github.onikenx.rascunhoamov

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

data class Ponto(val x: Float, val y: Float)
data class Linha(val cor: Int, val pontos: MutableList<Ponto>)

class AreaDesenho(context: Context, red: Int, green: Int, blue: Int) : View(context),
    GestureDetector.OnGestureListener {
    init {
        setBackgroundColor(Color.rgb(red, green, blue))
    }

    val paint = Paint(Paint.DITHER_FLAG)
        .also {
            it.color = Color.BLACK
            it.strokeWidth = 4.0f
            it.style = Paint.Style.FILL_AND_STROKE
        }

    val gestureDetector: GestureDetector =
        GestureDetector(context, this)

    val linha = mutableListOf<Ponto>()

    val desenho = mutableListOf<Linha>()

    val TAG = "GestureDetector"
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null)
            return
        desenho.filter{it.pontos.size>=2}.forEach{
//            var old = Ponto(-it.pontos[0].x, -it.pontos[0].y)
//            for (i in 1..it.pontos.size-1) {
//                if (it.pontos[i].x < 0)
//                    old = Ponto(-it.pontos[i].x, -it.pontos[i].y)
//                else {
//                    canvas?.drawLine(old.x, old.y, it.pontos[i].x, it.pontos[i].y, paint)
//                    old = it.pontos[i]
//                }
//            }
//
            var old = it.pontos[0]
            paint.color = it.cor
            for (i in 1..it.pontos.size-1){
                canvas.drawLine(old.x, old.y, it.pontos[i].x,  it.pontos[i].y, paint)
                old = it.pontos[i]
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (gestureDetector.onTouchEvent(event))
            return true
        return super.onTouchEvent(event)
    }


    override fun onShowPress(e: MotionEvent?) {
        Log.i(TAG, "onShowPress: ")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.i(TAG, "onSingleTapUp: ")
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.i(TAG, "onDown: ")
        if (e != null) {
            val cor = Color.rgb(
                Random.nextInt(
                    0,
                    255
                ),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
//            val cores = arrayOf(Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN)
//            val cor = cores[Random.nextInt(0, cores.size)]
            desenho.add(
                Linha(
                    cor,
                    mutableListOf(
                        Ponto(
                            e.x,
                            e.y
                        )
                    )
                )
            )
            invalidate()
        }
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.i(TAG, "onFling: ")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (e2 == null)
            return false
        Log.i(TAG, "onScroll: ${e2.x} ${e2.y}")
        desenho[desenho.size - 1].pontos.add(
            Ponto(
                e2.x,
                e2.y
            )
        )
        invalidate()
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.i(TAG, "onLongPress: ")
    }

}