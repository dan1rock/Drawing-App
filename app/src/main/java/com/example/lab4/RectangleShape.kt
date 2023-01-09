package com.example.lab4

import android.graphics.*

class RectangleShape : Shape() {
    init {
        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 5f

        fillPaint.color = Color.GREEN
        fillPaint.style = Paint.Style.FILL
        fillPaint.strokeWidth = 5f
    }

    override fun getIcon(): Int {
        return R.drawable.rectangle
    }

    override fun getName(): String {
        return "Rect"
    }

    override fun drawPreview(endPoint: PointF, canvas: Canvas?) {
        endPointF = endPoint;
        draw(canvas, previewPaint)
    }

    override fun finishDrawing() {
        DrawView.selectedFigure = RectangleShape()
    }

    override fun drawShape(canvas: Canvas?) {
        draw(canvas, fillPaint)
        draw(canvas, mainPaint)
    }

    private fun draw(canvas: Canvas?, paint: Paint ){
        canvas?.drawRect(
            startPointF!!.x - (endPointF!!.x - startPointF!!.x),
            startPointF!!.y - (endPointF!!.y - startPointF!!.y),
            endPointF!!.x,
            endPointF!!.y,
            paint
        )
    }
}