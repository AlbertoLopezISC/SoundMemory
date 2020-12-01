package mx.uaa.soundmemory

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.graphics.scale
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCartas(val datos: MutableList<Descripcion>,
                      private val clickListener: (Descripcion, Int) -> Unit):
    RecyclerView.Adapter<AdaptadorCartas.CartasViewHolder>(){

    class CartasViewHolder(val item: View): RecyclerView.ViewHolder(item){
        val imagenCarta: ImageView = item.findViewById(R.id.imgCarta)
        val txtId: TextView = item.findViewById(R.id.txtIdCarta)
        val txtDescripcion: TextView = item.findViewById(R.id.txtDescripcion)

        fun bindCarta(carta: Descripcion){
            var bitmap = BitmapFactory.decodeFile(carta.ruta)
            bitmap = bitmap.scale((bitmap.width * .1).toInt(),(bitmap.height * .1).toInt(),false)
            imagenCarta.setImageBitmap(bitmap)
            txtId.text = "Carta: ${carta.id}"
            txtDescripcion.text = "Descripcion: ${carta.descrip}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartasViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_carta, parent, false) as GridLayout
        return CartasViewHolder(item)
    }

    override fun onBindViewHolder(holder: CartasViewHolder, position: Int) {
        val carta = datos[position]
        holder.bindCarta(carta)
        holder.item.setOnClickListener{clickListener(carta, position)}
    }

    override fun getItemCount(): Int = datos.size
}