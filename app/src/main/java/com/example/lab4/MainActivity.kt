package com.example.lab4

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var currentMenu: Menu? = null
    private var shapeTable: BottomSheetDialog? = null
    private var layout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        currentMenu = menu
        selectDot()
        title = "Dot"
        shapeTable = BottomSheetDialog(this)
        shapeTable?.setContentView(R.layout.shapes_table)
        layout = shapeTable?.findViewById(R.id.table_layout)
        DrawView.mainClass = this
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.DOT, R.id.btnDOT -> selectDot()
            R.id.LINE, R.id.btnLINE -> selectLine()
            R.id.RECTANGLE, R.id.btnRECTANGLE -> selectRect()
            R.id.ELLIPSE, R.id.btnELLIPSE -> selectOval()
            R.id.LINEOO, R.id.btnLINEOO -> selectLineOO()
            R.id.CUBE, R.id.btnCUBE -> selectCube()
            R.id.btnTABLE -> shapeTable?.show()
            R.id.btnSAVE -> saveShapes()
            R.id.btnRESTORE -> loadShapes()
        }
        return true
    }

    private fun selectDot() {
        DrawView.selectedFigure = DotShape()
        changeCurrentIconColor(R.id.btnDOT, R.drawable.dot)
        title = "Dot"
    }

    private fun selectLine() {
        DrawView.selectedFigure = LineShape()
        changeCurrentIconColor(R.id.btnLINE, R.drawable.line)
        title = "Line"
    }

    private fun selectRect() {
        DrawView.selectedFigure = RectangleShape()
        changeCurrentIconColor(R.id.btnRECTANGLE, R.drawable.rectangle)
        title = "Rectangle"
    }

    private fun selectOval() {
        DrawView.selectedFigure = EllipseShape()
        changeCurrentIconColor(R.id.btnELLIPSE, R.drawable.ellipse)
        title = "Ellipse"
    }

    private fun selectLineOO() {
        DrawView.selectedFigure = LineOOShape()
        changeCurrentIconColor(R.id.btnLINEOO, R.drawable.lineoo)
        title = "OLineO"
    }

    private fun selectCube() {
        DrawView.selectedFigure = CubeShape()
        changeCurrentIconColor(R.id.btnCUBE, R.drawable.cube)
        title = "Cube"
    }

    private fun resetIcons() {
        currentMenu?.findItem(R.id.btnDOT)?.setIcon(R.drawable.dotw)
        currentMenu?.findItem(R.id.btnLINE)?.setIcon(R.drawable.linew)
        currentMenu?.findItem(R.id.btnRECTANGLE)?.setIcon(R.drawable.rectanglew)
        currentMenu?.findItem(R.id.btnELLIPSE)?.setIcon(R.drawable.ellipsew)
        currentMenu?.findItem(R.id.btnLINEOO)?.setIcon(R.drawable.lineoow)
        currentMenu?.findItem(R.id.btnCUBE)?.setIcon(R.drawable.cubew)
    }

    private fun changeCurrentIconColor(id: Int, icon: Int) {
        resetIcons()
        currentMenu?.findItem(id)?.setIcon(icon)
    }

    @SuppressLint("InflateParams")
    fun addTableItem(text: String, id: Shape?) {
        val view = layoutInflater.inflate(R.layout.table_item, null)
        val deleteButton = view.findViewById<Button>(R.id.delete)
        val selectButton = view.findViewById<Button>(R.id.select)
        val icon = view.findViewById<ImageView>(R.id.icon)
        icon.setImageResource(DrawView.selectedFigure?.getIcon()!!)
        view.findViewById<TextView>(R.id.text).text = text
        deleteButton.setOnClickListener {
            layout?.removeView(view)
            DrawView.figures.remove(id)
            DrawView.isEditing = false
            findViewById<DrawView>(R.id.drawView).invalidate()
        }
        selectButton.setOnClickListener {
            DrawView.selectedFigure = id
            DrawView.isEditing = true
            findViewById<DrawView>(R.id.drawView).invalidate()
        }
        layout?.addView(view)
    }

    private fun saveShapes() {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput("shapes.txt", Context.MODE_PRIVATE)
            for (figure in DrawView.figures) {
                val data = figure.getName() + " " + figure.startPointF?.x.toString() + " " +
                        figure.startPointF?.y.toString() + " " + figure.endPointF?.x.toString() + " " +
                        figure.endPointF?.y.toString()
                fileOutputStream.write(data.toByteArray())
                fileOutputStream.write(10)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadShapes() {
        shapeTable?.setContentView(R.layout.shapes_table)
        layout = shapeTable?.findViewById(R.id.table_layout)
        DrawView.figures.clear()
        val fileInputStream = openFileInput("shapes.txt")
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var text: String?
        while (run {
                text = bufferedReader.readLine()
                text
            } != null) {
            val args = text?.split("\\s".toRegex())?.toTypedArray()
            when (args!!.elementAt(0)) {
                "Dot" -> DrawView.selectedFigure = DotShape()
                "Line" -> DrawView.selectedFigure = LineShape()
                "Rect" -> DrawView.selectedFigure = RectangleShape()
                "Ellipse" -> DrawView.selectedFigure = EllipseShape()
                "LineOO" -> DrawView.selectedFigure = LineOOShape()
                "Cube" -> DrawView.selectedFigure = CubeShape()
            }
            DrawView.selectedFigure?.startPointF =
                PointF(args.elementAt(1).toFloat(), args.elementAt(2).toFloat())
            DrawView.selectedFigure?.endPointF =
                PointF(args.elementAt(3).toFloat(), args.elementAt(4).toFloat())

            val coordsString =
                "Start(" + DrawView.selectedFigure?.startPointF?.x?.roundToInt().toString() +
                        "," + DrawView.selectedFigure?.startPointF?.y?.roundToInt()
                    .toString() + ") End(" +
                        DrawView.selectedFigure?.endPointF?.x?.roundToInt().toString() + "," +
                        DrawView.selectedFigure?.endPointF?.y?.roundToInt().toString() + ")"

            addTableItem(coordsString, DrawView.selectedFigure)
            DrawView.figures.add(DrawView.selectedFigure!!)
        }
        DrawView.selectedFigure = DotShape()
        findViewById<DrawView>(R.id.drawView).invalidate()
    }
}