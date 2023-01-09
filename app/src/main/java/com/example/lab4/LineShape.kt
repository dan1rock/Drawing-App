package com.example.lab4

import android.graphics.*

class LineShape : Shape() {
    init {
        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 5f
    }

    override fun getIcon(): Int {
        return R.drawable.line
    }

    override fun getName(): String {
        return "Line"
    }

    override fun drawPreview(endPoint: PointF, canvas: Canvas?) {
        endPointF = endPoint;
        draw(canvas, previewPaint)
    }

    override fun finishDrawing() {
        DrawView.selectedFigure = LineShape()
    }

    override fun drawShape(canvas: Canvas?) {
        draw(canvas, mainPaint)
    }

    private fun draw(canvas: Canvas?, paint: Paint ){
        canvas?.drawLine(startPointF!!.x, startPointF!!.y, endPointF!!.x, endPointF!!.y, paint);
    }
}