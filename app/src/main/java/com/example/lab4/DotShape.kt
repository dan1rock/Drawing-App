package com.example.lab4

import android.graphics.*

class DotShape : Shape() {
    init {
        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 5f
        previewPaint.color = Color.RED
    }

    override fun getIcon(): Int {
        return R.drawable.dot
    }

    override fun getName(): String {
        return "Dot"
    }

    override fun drawPreview(endPoint: PointF, canvas: Canvas?) {
        endPointF = endPoint;
        draw(canvas, previewPaint)
    }

    override fun finishDrawing() {
        DrawView.selectedFigure = DotShape()
    }

    override fun drawShape(canvas: Canvas?) {
        draw(canvas, mainPaint)
    }

    private fun draw(canvas: Canvas?, paint: Paint ){
        canvas?.drawCircle(startPointF!!.x, startPointF!!.y, 2f, paint)
    }
}