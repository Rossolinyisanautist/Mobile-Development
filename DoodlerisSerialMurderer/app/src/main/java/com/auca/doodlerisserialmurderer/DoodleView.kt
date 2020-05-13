package com.auca.doodlerisserialmurderer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.hypot

class DoodleView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val brushPaint: Paint
    private val brush: Path

    private lateinit var backBuffer: Bitmap
    private lateinit var backBufferCanvas: Canvas
    private          var backBufferPaint: Paint = Paint()

    init {
        brush = Path()
        brushPaint = Paint(ANTI_ALIAS_FLAG)
            .apply {
            color = Color.argb(255,255, 0, 0)
            strokeJoin = Paint.Join.ROUND
            strokeWidth = 30f
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(fronBuffercanvas: Canvas) {
        super.onDraw(fronBuffercanvas)

        fronBuffercanvas.drawBitmap(backBuffer, 0f, 0f, backBufferPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (w > 0 && h > 0) {
            backBuffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            backBufferCanvas = Canvas(backBuffer)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
         when (event.action) {
             MotionEvent.ACTION_DOWN -> onTouchStarted(event)
             MotionEvent.ACTION_MOVE -> onTouchMoved(event)
             MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> onTouchEnded(event)
         }
        return true
    }

    private fun onTouchStarted(event: MotionEvent) {
        brush.moveTo(event.x, event.y)
    }

    private fun onTouchMoved(event: MotionEvent) {
        val x = event.x
        val previousX = if(event.historySize > 0) event.getHistoricalX(0) else x
        val dx = x - previousX

        val y = event.y
        val previousY = if(event.historySize > 0) event.getHistoricalY(0) else y
        val dy = y - previousY

        if(hypot(dx, dy) > 2.0f) {
            brush.quadTo(x + dx * 0.5f, y + dy * 0.5f, x, y)
            backBufferCanvas.drawPath(brush, brushPaint)
        }

        invalidate()
    }

    private fun onTouchEnded(event: MotionEvent) {
        brush.reset()
    }
}