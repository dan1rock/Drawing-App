package com.example.lab4

import android.graphics.*
import kotlin.math.sqrt

class LineOOShape: Shape() {
    private var lineVector: PointF? = null
    private var vectorLength: Float? = null

    init {
        mainPaint.color = Color.BLACK
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 5f

        fillPaint.color = Color.TRANSPARENT
        fillPaint.style = Paint.Style.FILL
        fillPaint.strokeWidth = 5f
    }

    override fun getIcon(): Int {
        return R.drawable.lineoo
    }

    override fun getName(): String {
        return "LineOO"
    }

    override fun drawPreview(endPoint: PointF, canvas: Canvas?) {
        endPointF = endPoint;
        drawLine(canvas, previewPaint)
        drawCircles(canvas, previewPaint)
    }

    override fun finishDrawing() {
        DrawView.selectedFigure = LineOOShape()
    }

    override fun drawShape(canvas: Canvas?) {
        drawLine(canvas, mainPaint)
        drawCircles(canvas, fillPaint)
        drawCircles(canvas, mainPaint)
    }

    private fun drawLine(canvas: Canvas?, paint: Paint){
        vectorLength = sqrt((endPointF!!.x - startPointF!!.x) * (endPointF!!.x - startPointF!!.x)
                    + (endPointF!!.y - startPointF!!.y) * (endPointF!!.y - startPointF!!.y))
        lineVector = if(vectorLength != 0f) {
            PointF((endPointF!!.x - startPointF!!.x) / vectorLength!! * 30f,
                (endPointF!!.y - startPointF!!.y) / vectorLength!! * 30f)
        } else PointF(0f, 0f)
        canvas?.drawLine(
            startPointF!!.x + lineVector!!.x,
            startPointF!!.y + lineVector!!.y,
            endPointF!!.x - lineVector!!.x,
            endPointF!!.y - lineVector!!.y,
            paint);
    }

    private fun drawCircles(canvas: Canvas?, paint: Paint){
        canvas?.drawCircle(startPointF!!.x, startPointF!!.y, 30f, paint)
        canvas?.drawCircle(endPointF!!.x, endPointF!!.y, 30f, paint)
    }
}