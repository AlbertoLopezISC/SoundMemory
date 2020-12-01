package mx.uaa.soundmemory

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import java.util.jar.Manifest

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val SELECT_FOTO = 1
    val mImages: Uri? = null
    var path: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstFragment.background = background.getBackground()
        activity?.window?.statusBarColor = background.getPrimaryDark()
        activity?.window?.navigationBarColor = background.getPrimary()

        view.findViewById<Button>(R.id.button_play).setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_menuJuegoFragment)
        }

        view.findViewById<Button>(R.id.button_instruccions).setOnClickListener {
            view.findViewById<Button>(R.id.button_instruccions).contentDescription = "Sera Facil comenzar a jugar," +
                    " primero veamos los movimientos que puedes hacer. Para este tutorial necesitas tener activo TalkBack." +
                    " Presiona siguiente para continuar, el boton esta localizado abajo a la derecha."
            Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }, 1000)
        }

        /*view.findViewById<Button>(R.id.cartasBoton).setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_MenuCartas)
        }

         */

        view.findViewById<Button>(R.id.cartasBoton).setOnClickListener{
            /*val aux =  MenuCartasFragment()
            fragmentManager?.beginTransaction()?.add(R.id.nav_host_fragment, aux)
            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,aux)?.commit()*/
            findNavController().navigate(R.id.action_FirstFragment_to_MenuCartasFragment)
        }

        view.findViewById<Button>(R.id.button_salir).setOnClickListener {
            System.exit(0)
        }

    }

}