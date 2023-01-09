package com.example.lab4

import android.graphics.*

class EllipseShape : Shape() {
    init {
        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 5f
    }

    override fun getIcon(): Int {
        return R.drawable.ellipse
    }

    override fun getName(): String {
        return "Ellipse"
    }

    override fun drawPreview(endPoint: PointF, canvas: Canvas?) {
        endPointF = endPoint;
        canvas?.drawRect(startPointF!!.x, startPointF!!.y, endPointF!!.x, endPointF!!.y, previewPaint)
        draw(canvas, mainPaint)
    }

    override fun finishDrawing() {
        DrawView.selectedFigure = EllipseShape()
    }

    override fun drawShape(canvas: Canvas?) {
        draw(canvas, mainPaint)
    }

    private fun draw(canvas: Canvas?, paint: Paint ){
        canvas?.drawOval(startPointF!!.x, startPointF!!.y, endPointF!!.x, endPointF!!.y, paint);
    }
}