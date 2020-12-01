package mx.uaa.soundmemory

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_menu_juego.*
import kotlinx.android.synthetic.main.menu_ajustes_fragment.*
import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuAjustesFragment : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_ajustes_fragment)
        onViewCreated()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    fun onViewCreated() {

        menuAjustes.background = background.getBackground()
        CambiarColor.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#0d15ff"),
                Color.parseColor("#54FFD4"),
                Color.parseColor("#0d15ff")
            )
        )
        CambiarColor2.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#ff3358"),
                Color.parseColor("#BAF4FF"),
                Color.parseColor("#ff3358")
            )
        )
        CambiarColor3.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#ff0a0a"),
                Color.parseColor("#f6ff45"),
                Color.parseColor("#ff0a0a")
            )
        )
        regresarInicio.setOnClickListener(){
            finish()
        }

        findViewById<Button>(R.id.CambiarColor).setOnClickListener(){
//************************************************************************
            cambiarColor("#050971","#080FD5","#BB86FC")

            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#0d15ff"),
                    Color.parseColor("#54FFD4"),
                    Color.parseColor("#0d15ff")
                )
            )
            gradientDrawable.cornerRadius = 0f
            background.setBackground(gradientDrawable)
            background.setPrimary(Color.parseColor("#080FD5"))
            background.setPrimaryDark(Color.parseColor("#050971"))
            menuAjustes.background = gradientDrawable


        }

        CambiarColor2.setOnClickListener(){
            cambiarColor("#C60B2D","#F31C43","#BB86FC")
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#ff3358"),
                    Color.parseColor("#BAF4FF"),
                    Color.parseColor("#ff3358")
                )
            )
            gradientDrawable.cornerRadius = 0f
            background.setBackground(gradientDrawable)
            background.setPrimary(Color.parseColor("#F31C43"))
            background.setPrimaryDark(Color.parseColor("#C60B2D"))
            menuAjustes.background = gradientDrawable

        }

        CambiarColor3.setOnClickListener(){
        cambiarColor("#D32D04","#E60A0A","#BB86FC")
        val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#ff0a0a"),
                    Color.parseColor("#f6ff45"),
                    Color.parseColor("#ff0a0a")
                )
            )
            gradientDrawable.cornerRadius = 0f
            background.setBackground(gradientDrawable)
            background.setPrimary(Color.parseColor("#E60A0A"))
            background.setPrimaryDark(Color.parseColor("#D32D04"))
            menuAjustes.background = gradientDrawable


        }

        ColorAceptar.setOnClickListener(){
            if(color1.text != null && color.text != null ){
                var c1 = 0
                var c2 = 0
                try {
                    c1 = Color.parseColor(color1.text.toString())
                } catch (e: Exception) {
                    Toast.makeText(this, "Color invalido.", Toast.LENGTH_SHORT).show()
                }
                try {
                    c2 = Color.parseColor(color.text.toString())
                } catch (e: Exception){
                    Toast.makeText(this, "Color invalido.", Toast.LENGTH_SHORT).show()
                }
                if(c1 != 0 && c2 != 0){
                    val gradientDrawable = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        intArrayOf(
                            Color.parseColor(color1.text.toString()),
                            Color.parseColor(color.text.toString()),
                            Color.parseColor(color1.text.toString())
                        )
                    )
                    gradientDrawable.cornerRadius = 0f
                    background.setBackground(gradientDrawable)
                    background.setPrimary(Color.parseColor("#988986"))
                    background.setPrimaryDark(Color.parseColor("#574C4A"))
                    menuAjustes.background = gradientDrawable
                    cambiarColor("#574C4A","#988986","#FFFFFF")
                }

            }
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cambiarColor(primaryDark: String, primary: String, background: String){
            window?.statusBarColor = Color.parseColor(primaryDark)
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(primary)))
            window?.setBackgroundDrawable(ColorDrawable(Color.parseColor(background)))
            window?.navigationBarColor = Color.parseColor(primary)
        Toast.makeText(this,"Color cambiado", Toast.LENGTH_SHORT).show()
    }
}