package mx.uaa.soundmemory

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_jugar.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var cont = 0
    var c1 = false
    var c2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tapa: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.tapa2, null)
        val drawable: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.barco, null)
        val imagen2: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.barco_de_papel, null)
        secondFragment.background = background.getBackground()


        Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
            textViewInstrucciones.requestFocus(1);
        }, 1000)
        view.findViewById<Button>(R.id.button_regresar).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        view.findViewById<Button>(R.id.button_siguiente).setOnClickListener {
            if(cont == 0){
                button_siguiente.contentDescription = "Muy bien, ahora apareceran una carta tapada" +
                        "en el centro a la izquierda de la pantalla. Presionala  para escuchar cual numero es. Presiona dos veces" +
                        "para destaparla y ver lo que es. Cuando lo hayas hecho, presiona siguiente"
                view.findViewById<TextView>(R.id.textViewInstrucciones).text = "Muy bien, ahora apareceran una carta tapada" +
                        "en el centro a la izquierda de la pantalla. Presionala para escuchar cual numero es. Presiona dos veces" +
                        "para destaparla y ver lo que es. Cuando lo hayas hecho, presiona siguiente"
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    if(button_siguiente != null){
                        button_siguiente.contentDescription = "Boton siguiente";
                    }
                }, 15000)
                carta1Instrucciones.visibility = View.VISIBLE;
                cont++;
            } else if(cont == 1){
                button_siguiente.contentDescription = "Muy bien, ahora que ya viste que carta es, aparecera" +
                        "la siguiente carta en el centro a la derecha y tendras que destaparla de nuevo.";
                view.findViewById<TextView>(R.id.textViewInstrucciones).text = "Muy bien, ahora que ya viste que carta es, aparecera" +
                        "la siguiente carta en el centro a la derecha y tendras que destaparla de nuevo.";
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    if(button_siguiente != null){
                        button_siguiente.contentDescription = "Boton siguiente";
                    }
                }, 10000)
                carta2Instrucciones.visibility = View.VISIBLE;
                cont++;
                c1 = false
                c2 = false
            } else if( cont == 2){
                button_siguiente.contentDescription = "Ahora las cartas vuelven a estar tapadas, vuelve a destapar las 2" +
                        "para que identifiques cuando hayas fallado";
                textViewInstrucciones.text = "Ahora las cartas vuelven a estar tapaas, vuelve a destapar las 2" +
                        "para que identifiques cuando hayas fallado";
                c1 = false
                c2 = false
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    if(button_siguiente != null){
                        button_siguiente.contentDescription = "Boton siguiente";
                    }
                }, 5000)
                carta1Instrucciones.background = tapa
                carta2Instrucciones.background = tapa
                carta1Instrucciones.contentDescription = "Carta uno Tapada"
                carta2Instrucciones.contentDescription = "Carta dos Tapada"
                cont++;
            }
        }
        view.findViewById<ImageButton>(R.id.carta1Instrucciones).setOnClickListener(){
            if(cont != 3) {
                carta1Instrucciones.background = drawable;
                carta1Instrucciones.contentDescription = "Carta destapada, Gran barco de 4 chimeneas surcando el mar.";
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    carta1Instrucciones.requestFocus();
                }, 1000)
                c1 = true;
            } else {
                c1 = true;
                carta1Instrucciones.background = drawable;
                carta1Instrucciones.contentDescription = "Carta destapada, Gran barco de 4 chimeneas surcando el mar."
                Handler().postDelayed(Runnable {
                    if(this.c2) {
                        carta1Instrucciones.contentDescription ="Ahora las cartas lucen diferente. Por lo tanto nos hemos equivocado." +
                                "En el nivel facil esto no causara ninguna penalizacion, pero si quisieras un mayor reto, en el nivel dificil" +
                                "esto quitara 2 puntos a la puntuacion que lleves. Listo. Hemos terminado con el tutorial, ahora puedes presionar" +
                                "el boton Regresar para ir al menu de inicio";
                        textViewInstrucciones.text = "Ahora las cartas lucen diferente. Por lo tanto nos hemos equivocado." +
                                "En el nivel facil esto no causara ninguna penalizacion, pero si quisieras un mayor reto, en el nivel dificil" +
                                "esto quitara 2 puntos a la puntuacion que lleves. Listo. Hemos terminado con el tutorial, ahora puedes presionar" +
                                "el boton Regresar para ir al menu de inicio  ";
                        Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                            carta1Instrucciones.contentDescription = "Carta destapada, Gran barco de 4 chimeneas surcando el mar.";
                        }, 1000)
                    }
                }, 1000)
            }

        }
        carta2Instrucciones.setOnClickListener(){
            if(cont != 3) {
                carta2Instrucciones.background = drawable;
                carta2Instrucciones.contentDescription = "Carta destapada, Barco de 4 chimeneas surcando el mar.";
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    carta2Instrucciones.contentDescription =
                        "Excelente, hemos encontrado un par. Esto sumara 5 puntos a tu puntuacion, ahora" +
                                "veremos lo que pasa cuando no encontramos un par. Presiona siguiente para continuar.";
                    view.findViewById<TextView>(R.id.textViewInstrucciones).text =
                        "Excelente, hemos encontrado un par, ahora" +
                                "veremos lo que pasa cuando no encontramos un par. Presiona siguiente para continuar.";

                }, 1000)
            } else {
                this.c2 = true;
                carta2Instrucciones.background = imagen2;
                carta2Instrucciones.contentDescription = "Carta destapada. Pequeño barco de papel sobre el agua";
                Handler().postDelayed(Runnable {
                    if(c1) {
                        carta2Instrucciones.contentDescription ="Ahora las cartas lucen diferente. Por lo tanto nos hemos equivocado." +
                                "En el nivel facil esto no causara ninguna penalizacion, pero si quisieras un mayor reto, en el nivel dificil" +
                                "esto quitara 2 puntos a la puntuacion que lleves. Listo. Hemos terminado con el tutorial, ahora puedes presionar" +
                                "el boton Regresar para ir al menu de inicio  ";
                        textViewInstrucciones.text = "Ahora las cartas lucen diferente. Por lo tanto nos hemos equivocado." +
                                "En el nivel facil esto no causara ninguna penalizacion, pero si quisieras un mayor reto, en el nivel dificil" +
                                "esto quitara 2 puntos a la puntuacion que lleves. Listo. Hemos terminado con el tutorial, ahora puedes presionar" +
                                "el boton Regresar para ir al menu de inicio  ";
                        Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                            carta2Instrucciones.contentDescription = "Carta destapada. Pequeño barco de papel sobre el agua";
                        }, 1000)
                        println("entra")
                    }
                }, 1000)
            }
        }
    }
}