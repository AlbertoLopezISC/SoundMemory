package mx.uaa.soundmemory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_menu_cartas.*
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class MenuCartasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_menu_cartas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MenuCartas.background = background.getBackground()

        view.findViewById<Button>(R.id.button_Agregar).setOnClickListener{
            //fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, AgregarCartasFragment())?.commit()
            findNavController().navigate(R.id.action_MenuCartasFragment_to_AgregarCartasFragment)
        }

        view.findViewById<Button>(R.id.btnEditarCartas).setOnClickListener {
            //fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, EditarCartasFragment())?.commit()
            findNavController().navigate(R.id.action_MenuCartasFragment_to_editarCartasFragment)
        }

    }
}