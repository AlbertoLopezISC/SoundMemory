package mx.uaa.soundmemory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_menu_cartas.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class EditarCartasFragment : Fragment() {

    private lateinit var recView: RecyclerView
    private lateinit var applicacion: AccederApp


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_cartas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applicacion = AccederApp(requireActivity().applicationContext)
        var adaptador: AdaptadorCartas?

        lifecycleScope.launch{
            recView = view.findViewById(R.id.recCartas)

            val basecompleta = applicacion.room.descripcionDao().getAll()

            adaptador = AdaptadorCartas(basecompleta) { carta: Descripcion, position: Int ->
                val bundle = bundleOf("carta" to carta, "position" to position);
                val aux = AgregarCartasFragment()
                //enviando parametros o datos entre los fragmentos
                aux.arguments = bundle
                //fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, aux )?.commit()
                findNavController().navigate(
                    R.id.action_editarCartasFragment_to_AgregarCartasFragment,
                    bundle
                );
                Toast.makeText(
                    applicacion.mContext,
                    "presiono Carta: ${position + 1}, ahora puedes editarla.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            recView.setHasFixedSize(true)
            recView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            recView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
            recView.adapter = adaptador

        }

    }
}