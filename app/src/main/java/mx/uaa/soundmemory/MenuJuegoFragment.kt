package mx.uaa.soundmemory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_menu_juego.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuJuegoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_juego, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuJuego.background = background.getBackground()
        botonFacil.setOnClickListener {


            val bundle = bundleOf("tamaño" to 6,  "puntos" to 0);
            findNavController().navigate(R.id.action_menuJuegoFragment_to_jugar, bundle);

        }
        botonNormal.setOnClickListener {


            val bundle = bundleOf("tamaño" to 8,  "puntos" to 2);
            findNavController().navigate(R.id.action_menuJuegoFragment_to_jugar, bundle);

        }
        botonDificil.setOnClickListener {


            val bundle = bundleOf("tamaño" to 12,  "puntos" to 4);
            findNavController().navigate(R.id.action_menuJuegoFragment_to_jugar, bundle);

        }
    }
}