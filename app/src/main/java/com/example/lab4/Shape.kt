package com.example.lab4

import android.graphics.*

abstract class Shape() {
    val previewPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    protected var mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    protected var fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var startPointF: PointF? = null
    var endPointF: PointF? = null

    init {
        previewPaint.color = Color.BLACK
        previewPaint.style = Paint.Style.STROKE
        previewPaint.strokeWidth = 5f
        previewPaint.pathEffect = DashPathEffect(floatArrayOf(15f, 10f), 0f)
    }

    abstract fun drawPreview(endPoint: PointF, canvas: Canvas?)
    abstract fun finishDrawing()
    abstract fun drawShape(canvas: Canvas?)
    abstract fun getIcon() : Int
    abstract fun getName() : String
    fun startDrawing(startPoint: PointF){
        startPointF = startPoint;
        endPointF = startPoint;
    }
}