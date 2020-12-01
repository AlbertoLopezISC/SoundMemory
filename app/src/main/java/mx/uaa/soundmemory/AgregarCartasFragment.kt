package mx.uaa.soundmemory

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_agregar_carta.*
// estas agregue para corrutinas

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import java.io.Serializable


/**
 * A simple [Fragment] subclass.

 * create an instance of this fragment.
 */
class AgregarCartasFragment : Fragment() {
    var applicacion: AccederApp? = null
    val SELECT_FOTO = 1
    val mImages: Uri? = null
    var path: String? = null
    var filePath = ""
    private var carta: Descripcion? = null
    private var position: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_carta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applicacion = AccederApp(requireActivity().applicationContext)
        val txtDescripcion: EditText = view.findViewById(R.id.inputdescripcion)
        val inputDescripcion : Descripcion?= Descripcion(0,"","")
        val btnEliminar: Button = view.findViewById(R.id.btnEliminarCarta)
        EditarCartas.background = background.getBackground()


        carta =  arguments?.getSerializable("carta") as Descripcion?
        position = arguments?.getInt("position")

        context?.let { ContextCompat.checkSelfPermission(it,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED }

        ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        if (carta != null && position != null){
            ponerimagen.setImageBitmap(BitmapFactory.decodeFile(carta?.ruta))
            txtDescripcion.setText(carta?.descrip)
            btnEliminar.visibility = View.VISIBLE
        }


        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                view.findViewById<Button>(R.id.button_cargarimagen).setOnClickListener{
                    val intent: Intent = Intent(
                        Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,mImages)
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                    intent.setType("image/*")
                    startActivityForResult(Intent.createChooser(intent, "Elige aplicacion"), SELECT_FOTO)

                }
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                val permisos = android.Manifest.permission.READ_EXTERNAL_STORAGE
                activity?.let { ActivityCompat.requestPermissions(it, arrayOf(permisos), 50) }
            }
        }

        buttonChido.setOnClickListener {
            if (carta != null && position != null) {
                filePath = carta!!.ruta
                if (inputDescripcion != null&& filePath != "") {
                    lifecycleScope.launch{
                        applicacion?.room?.descripcionDao()?.updateCardById(carta?.id, txtDescripcion.text.toString(), filePath)
                    }.invokeOnCompletion {
                        Toast.makeText(context, "Listo, se ha editado la carta.",Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    }

                }
            } else {
                //OJO AQUI
                //

                inputDescripcion?.descrip = inputdescripcion.text.toString()
                //

                if(filePath== ""){
                    Toast.makeText(
                        context,
                        "Primero selecciona una imagen",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // aqui va la corrutina
                if (inputDescripcion != null&& filePath != "") {
                    lifecycleScope.launch{
                        val d = Descripcion(null, txtDescripcion.text.toString(), filePath)
                        applicacion?.room?.descripcionDao()?.insert(d)
                        Toast.makeText(context, "Listo, se ha agregado tu nueva carta.",Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                        //fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, aux)?.commit()
                    }

                }

            }

        }

        btnEliminar.setOnClickListener{
            lifecycleScope.launch {
                applicacion?.room?.descripcionDao()?.deleteCardById(carta?.id)
                Toast.makeText(context, "Se eliminó la carta: ${carta?.id}.", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
                //fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,aux)?.commit()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        when(requestCode){
            SELECT_FOTO -> {
                if (resultCode == RESULT_OK) {
                    //Tomar el nombre de la foto seleccionada
                    val selectedImage: Uri? = data?.data
                    val wholeID = DocumentsContract.getDocumentId(selectedImage)
                    val id = wholeID.split(":".toRegex()).toTypedArray()[1]

                    val column = arrayOf(MediaStore.Images.Media.DATA)
                    val sel = MediaStore.Images.Media._ID + "=?"

                    val cursor: Cursor? = activity?.contentResolver?.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,column, sel, arrayOf(id), null)
                    //  var filePath = ""
                    val columnIndex = cursor?.getColumnIndex(column[0])
                    if (cursor != null) {
                        if(cursor.moveToFirst())
                            filePath = cursor.getString(columnIndex!!)
                    }
                    this.path = filePath
                    println(filePath)
                    val bitmap = BitmapFactory.decodeFile(path); // Crea un mapa de bits y lo asigna a un imagenView
                    ponerimagen.setImageBitmap(bitmap);
                    // imgProducto.setImageBitmap(bitmap);
                }
            }
        }
    }

}