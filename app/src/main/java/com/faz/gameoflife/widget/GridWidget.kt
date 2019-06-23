package com.faz.gameoflife.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GridWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val cellPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var gridColor = DEFAULT_GRID_COLOR

    var cellColor = DEFAULT_CELL_COLOR

    var cellClickListener: ((row: Int, col: Int) -> Unit)? = null

    var data = listOf<List<Int>>()
        set(value) {
            field = value
            invalidate()
        }

    private var WIDTH: Int = 0

    private var HEIGHT: Int = 0

    private fun getRowCount() = data.size

    private fun getColCount() = data[0].size

    private fun getCellSize() = WIDTH / getColCount()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (data.isEmpty()) return

        cellPaint.style = Paint.Style.FILL

        val rowCount = getRowCount()
        val colCount = getColCount()
        val cellSize = getCellSize()

        // cells
        cellPaint.color = cellColor
        for (i in 0 until rowCount) {
            for (j in 0 until colCount) {
                if (data[i][j] == 1) {
                    canvas.drawRect(
                        j * cellSize.toFloat(),
                        i * cellSize.toFloat(),
                        (j + 1) * cellSize.toFloat(),
                        (i + 1) * cellSize.toFloat(),
                        cellPaint
                    )
                }
            }
        }

        // grid
        gridPaint.style = Paint.Style.STROKE
        gridPaint.color = gridColor
        gridPaint.strokeWidth = 1f

        // horizontal lines
        for (i in 0..rowCount) {
            canvas.drawLine(
                0f,
                i * cellSize.toFloat(),
                colCount * cellSize.toFloat(),
                i * cellSize.toFloat(),
                gridPaint
            )
        }

        // vertical lines
        for (i in 0..colCount) {
            canvas.drawLine(
                i * cellSize.toFloat(),
                0f,
                i * cellSize.toFloat(),
                rowCount * cellSize.toFloat(),
                gridPaint
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (data.isEmpty()) return super.onTouchEvent(event)
        val cellSize = getCellSize()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x.toInt()
                val y = event.y.toInt()
                cellClickListener?.invoke(
                    y / cellSize,
                    x / cellSize
                )
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        WIDTH = w
        HEIGHT = h
    }

    companion object {
        val DEFAULT_GRID_COLOR = Color.parseColor("#000000")
        val DEFAULT_CELL_COLOR = Color.parseColor("#00AA00")
    }
}