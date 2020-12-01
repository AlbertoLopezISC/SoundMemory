package mx.uaa.soundmemory

import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    //aqui
    private var applicacion: AccederApp? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        //aqui es donde guardotodo el listado de la base de datos

        applicacion = AccederApp(applicationContext)
        window?.statusBarColor = background.getPrimaryDark()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(background.getPrimary()))
        window?.navigationBarColor = background.getPrimary()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                /*setContentView(R.layout.menu_ajustes_fragment)*/
                startActivity(Intent(this,MenuAjustesFragment::class.java))
                return false
            }
            R.id.acerca_de -> {
                startActivity(Intent(this, Ajustes::class.java))
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class background  // private constructor
private constructor() {
    companion object {
        private var primary = Color.parseColor("#03DAC5")
        private var primaryDark = Color.parseColor("#FF018786")
        private  var bg = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#eeffe3"),
                Color.parseColor("#bffff8"),
                Color.parseColor("#bdd9ff"),
                Color.parseColor("#eeffe3"),
                Color.parseColor("#bffff8")
            )
        )
        fun setBackground(`val`: GradientDrawable) {
            bg = `val`
        }

        fun getBackground(): GradientDrawable {
            return bg
        }
        fun setPrimary(`val`: Int){
            primary = `val`
        }
        fun getPrimary(): Int{
            return primary
        }
        fun setPrimaryDark(`val`: Int){
            primaryDark = `val`
        }
        fun getPrimaryDark(): Int{
            return primaryDark
        }
    }
}
