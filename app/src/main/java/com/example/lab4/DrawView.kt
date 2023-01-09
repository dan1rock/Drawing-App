package com.example.lab4

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.roundToInt


class DrawView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var startPoint: PointF? = null
    private var endPoint: PointF? = null
    private var isDrawing = false

    companion object {
        var selectedFigure: Shape? = null
        var mainClass: MainActivity? = null
        val figures = mutableListOf<Shape>()
        var isEditing = false
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (figure in figures) if (figure != selectedFigure) figure.drawShape(canvas)
        if (isDrawing) {
            selectedFigure?.drawPreview(endPoint!!, canvas);
        } else if(isEditing) {
            selectedFigure?.drawPreview(selectedFigure?.endPointF!!, canvas);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                startPoint = PointF(event.x, event.y)
                endPoint = PointF(event.x, event.y)
                isDrawing = true
                selectedFigure?.startDrawing(startPoint!!)

                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                endPoint!!.x = event.x
                endPoint!!.y = event.y

                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                endPoint!!.x = event.x
                endPoint!!.y = event.y
                isDrawing = false
                if (!isEditing) {
                    val coordsString =
                        "Start(" + selectedFigure?.startPointF?.x?.roundToInt().toString() +
                                "," + selectedFigure?.startPointF?.y?.roundToInt()
                            .toString() + ") End(" +
                                selectedFigure?.endPointF?.x?.roundToInt().toString() + "," +
                                selectedFigure?.endPointF?.y?.roundToInt().toString() + ")"
                    mainClass?.addTableItem(coordsString, selectedFigure)
                    figures.add(selectedFigure!!)
                } else isEditing = false
                selectedFigure?.finishDrawing()

                invalidate()
            }
            else -> {}
        }
        return true
    }
}