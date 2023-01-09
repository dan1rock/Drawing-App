package com.example.lab4

import android.graphics.*
import kotlin.math.abs

class CubeShape: Shape() {
    private var endPoint: Float? = null
    private var startPoint: Float? = null
    private var signX: Int? = null
    private var signY: Int? = null

    init {
        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 5f
    }

    override fun getIcon(): Int {
        return R.drawable.cube
    }

    override fun getName(): String {
        return "Cube"
    }

    override fun drawPreview(endPoint: PointF, canvas: Canvas?) {
        endPointF = endPoint;
        draw(canvas, previewPaint)
    }

    override fun finishDrawing() {
        DrawView.selectedFigure = CubeShape()
    }

    override fun drawShape(canvas: Canvas?) {
        draw(canvas, mainPaint)
    }

    private fun draw(canvas: Canvas?, paint: Paint){
        if(abs(startPointF!!.x - endPointF!!.x) < abs(startPointF!!.y - endPointF!!.y)) {
            signX = if((endPointF!!.x < startPointF!!.x) && (endPointF!!.y > startPointF!!.y) ||
                ((endPointF!!.x > startPointF!!.x) && (endPointF!!.y < startPointF!!.y))) -1
            else 1
            signY = 1
            startPoint = startPointF!!.x
            endPoint = endPointF!!.x
        }
        else {
            signY = if(((endPointF!!.x < startPointF!!.x) && (endPointF!!.y > startPointF!!.y)) ||
                ((endPointF!!.x > startPointF!!.x) && (endPointF!!.y < startPointF!!.y))) -1
            else 1
            signX = 1
            startPoint = startPointF!!.y
            endPoint = endPointF!!.y
        }
        canvas?.drawRect(
            startPointF!!.x,
            startPointF!!.y,
            startPointF!!.x - (startPoint!! - endPoint!!) / 5 * 4 * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) / 5 * 4 * signX!!,
            paint
        )
        canvas?.drawRect(
            startPointF!!.x - (startPoint!! - endPoint!!) / 5 * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) / 5 * signX!!,
            startPointF!!.x - (startPoint!! - endPoint!!) * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) * signX!!,
            paint
        )
        canvas?.drawLine(
            startPointF!!.x,
            startPointF!!.y,
            startPointF!!.x - (startPoint!! - endPoint!!) / 5 * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) / 5 * signX!!,
            paint)
        canvas?.drawLine(
            startPointF!!.x - (startPoint!! - endPoint!!) / 5 * 4 * signY!!,
            startPointF!!.y,
            startPointF!!.x - (startPoint!! - endPoint!!) * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) / 5 * signX!!,
            paint)
        canvas?.drawLine(
            startPointF!!.x,
            startPointF!!.y - (startPoint!! - endPoint!!) / 5 * 4 * signX!!,
            startPointF!!.x - (startPoint!! - endPoint!!) / 5 * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) * signX!!,
            paint)
        canvas?.drawLine(
            startPointF!!.x - (startPoint!! - endPoint!!) / 5 * 4 * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) / 5 * 4 * signX!!,
            startPointF!!.x - (startPoint!! - endPoint!!) * signY!!,
            startPointF!!.y - (startPoint!! - endPoint!!) * signX!!,
            paint)
    }
}