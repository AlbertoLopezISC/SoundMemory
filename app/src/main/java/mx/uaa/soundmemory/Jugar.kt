package mx.uaa.soundmemory

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_jugar.*
import kotlinx.coroutines.launch


data class carta(val descripcion: String, val imagen : Drawable?, var par : Int)

class jugar : AppCompatActivity() {

    private lateinit var applicacion: AccederApp //variable para consultar la base de datos
    var imagen: ImageView? = null

    private var mazo = ArrayList<carta>()
    private var baraja = ArrayList<carta>()
    var cartaSeleccionadaUno: ImageButton? = null     //con estas variables guardamos los botones seleccionados
    var cartaSeleccionadaDos: ImageButton? = null     //******

    var cartaUnoPos :String = ""
    var cartaDosPos : String = "version 1.2"
    var cartaUno: Int = -1   //Con estas variables checamos si son cartas iguales
    var cartaDos: Int = -1   //**************************************************
    var puntuacion: Int = 0
    var baraja_numeros = arrayListOf<Int>()  //array que contiene los numeros con los cuales compararemos si son las mismas cartas
    var tamano_baraja :Int = 0
    var baraja_botones = arrayListOf<ImageButton>()

    var rnds: Int = 0  //variable para generar numeros aleatorios
    var repetidas = 0 //variable para generar los numeros aleatorios
    var cartasSobrantes : Int = 0



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugar)
        jugarFragment.background = background.getBackground()
        window?.statusBarColor = background.getPrimaryDark()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(background.getPrimary()))
        window?.navigationBarColor = background.getPrimary()


        val uno = 1
        applicacion = AccederApp(this)
        lifecycleScope.launch {
            val basecompleta = applicacion.room.descripcionDao().getAll()
            println("tamaño ${basecompleta.size}")
            for(carta in basecompleta){

                val bitmap = BitmapFactory.decodeFile(carta.ruta)
                val d : Drawable = BitmapDrawable(getResources(), bitmap);
                mazo.add(carta(carta.descrip,d, -1))

            }



            println("si pasa por ahi : $uno")



            val tapa:  Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.fondo_opcional, null)
            val imagen6: Drawable = resources.getDrawable(R.drawable.sol)
            val imagen1: Drawable = resources.getDrawable(R.drawable.barco)
            val imagen2: Drawable =  resources.getDrawable(R.drawable.barco_de_papel)
            val imagen3: Drawable = resources.getDrawable(R.drawable.pajaro_carpintero)
            val imagen4: Drawable = resources.getDrawable(R.drawable.pajaro_carpintero_colorido)
            val imagen5: Drawable = resources.getDrawable(R.drawable.nube)

            mazo.add(carta("barco mercantil",imagen1, -1))
            mazo.add(carta("barco de papel",imagen2, -1))
            mazo.add(carta("pajaro carpintero",imagen3, -1))
            mazo.add(carta("Pajaro carpintero de varios colores",imagen4, -1))
            mazo.add(carta("nube esponjada",imagen5, -1))
            mazo.add(carta("Sol resplandeciente",imagen6, -1))



            //capturando lo que nos llego de parametro
            val objetIntent: Intent= intent
            tamano_baraja = objetIntent.getIntExtra("tamaño",12)
            var puntos = objetIntent.getIntExtra("puntos", 0)
            cartasSobrantes = tamano_baraja/2


            //Boton para salir del juego
            Boton_salida.setOnClickListener {
                //Lineas de codigo para llamar a la actividad principal pero sin liberar memoria
                /*val intento1 = Intent(this, MainActivity::class.java)
                startActivity(intento1)*/
                //Ahora se termina la acctividad, regresando a la anterior. Esto permite liberar memoria
                finish()
            }

            //**********
            //Guardamos todos los botones en un array
            var totalBotones= arrayListOf<ImageButton>(
                button1,
                button2,
                button3,
                button4,
                button5,
                button6,
                button7,
                button8,
                button9,
                button10,
                button11,
                button12
            )
            // definiendo tamaño de los botones
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            val width = metrics.widthPixels // ancho absoluto en pixels
            val height = metrics.heightPixels // alto absoluto en pixels
            println("ancho de la pantalla =  $width")
            println("ancho de la pantalla =  $height")
            val w = (width - 60) / 3
            val h = (height - 540) / 4
            //*******************************


            //***************

            //Barajeando las cartas
            // for para generar los numeros aleatorios para las posiciones de las cartas
            for(i in 0..tamano_baraja-1) {
                do {
                    repetidas = 0
                    rnds = (0..( tamano_baraja / 2 )-1).random()

                    for (index in baraja_numeros) {
                        if (index == rnds)
                            repetidas++
                    }
                    if (repetidas < 2)
                        baraja_numeros.add(rnds)
                }while(repetidas>1)
            } //Fin del for*************


            for(i in 0..tamano_baraja/2-1) {
                do {
                    repetidas = 0
                    rnds = (0..mazo.size - 1).random()
                    var posibleCarta = mazo[rnds]
                    for (cartaAuxiliar in baraja) {
                        if (cartaAuxiliar == posibleCarta)
                            repetidas++
                    }
                    if (repetidas != 1)
                        baraja.add(posibleCarta)
                }while(repetidas > 0)
            }

            //fin de barajear **********

            //Comprobando en la terminal que se hayan generado los numeros correctamente
            var tamaño=baraja_numeros.size
            println("el tamaño de la baraja es  de $tamaño")
            println("el tamaño del mazo  es  de ${mazo.size}")
            for (numero in baraja_numeros)
                println(numero)
            //fin de la comprobacion***************



            //Mostrando el numero de cartas segun la dificultad seleccionada
            for((index,carta) in totalBotones.withIndex())
            {
                if(index < tamano_baraja){
                    carta.setContentDescription("carta número ${index + 1}")
                    carta.setTag("carta ${index + 1}")
                    println(carta.getTag())
                    println("boton:$index altura: $h  ancho: $w")
                    val params = carta.layoutParams
                    params.width = w
                    params.height = h
                    carta.layoutParams = params
                    baraja_botones.add(carta)
                }else {
                    carta.setVisibility(View.INVISIBLE)
                }


            }
            //******************



            //Realizando cambios al Boton  cuando se da click
            for((index,boton) in baraja_botones.withIndex()){
                boton.setOnClickListener {

                    if(cartaUno == -1) { //en caso de ser la primera carta que se escogió
                        cartaUno = baraja_numeros[index]    // Guardamos que es lo que esconde esa carta
                        cartaSeleccionadaUno=boton
                        cartaSeleccionadaUno?.setBackground(baraja[cartaUno].imagen) //Cambiamos la imagen (volteamos la carta)
                        cartaUnoPos=boton.getContentDescription().toString()
                        boton.setContentDescription(baraja[cartaUno].descripcion) //cambiamos la descripcion
                    } //fin del if**********
                    else if(boton != cartaSeleccionadaUno  && cartaDos == -1) { //en caso de ser la segunda carta que se escogió


                        cartaDos = baraja_numeros[index]    // Guardamos que es lo que esconde esa carta
                        cartaSeleccionadaDos=boton
                        cartaSeleccionadaDos?.setBackground(baraja[cartaDos].imagen) //Cambiamos la imagen (volteamos la carta)
                        cartaDosPos=boton.getContentDescription().toString()
                        boton.setContentDescription(baraja[cartaDos].descripcion)


                        if(cartaUno == cartaDos  ) { //En caso de que hayan sido las mismas


                            cartasSobrantes-=1
                            //empieza retardo
                            cartaSeleccionadaDos?.setContentDescription("Acertaste!!")

                            val dandler = Handler() // Comienzo del retardo
                            dandler.postDelayed(Runnable { // Do something after 5s = 5000ms
                                cartaSeleccionadaUno?.setVisibility(View.INVISIBLE)   //En caso de que sean las mismas cartas, procedemos a borrar estas
                                cartaSeleccionadaDos?.setVisibility(View.INVISIBLE)
                                puntuacion += 5
                                textoPuntuacion?.setText("Puntuación: $puntuacion")
                                textoPuntuacion.requestFocus()
                                cartaUno = -1
                                cartaDos = -1

                                if(cartasSobrantes== 0 ){
                                    renglonUno.visibility=View.GONE
                                    renglonGanar.visibility=View.VISIBLE
                                    textoGanar.contentDescription = "Ganaste, tu puntuación fue de $puntuacion puntos"
                                    textoGanar.visibility=View.VISIBLE
                                    textoGanar.setContentDescription("Felicidades Ganaste!!")
                                }


                            }, 3000) //Fin del retardo

                            // cartaSeleccionadaUno?.setEnabled(false)               // setEnabled
                            // cartaSeleccionadaDos?.setEnabled(false)               //

                        } else { //En caso de que NO sea el mismo
                            val handler = Handler()
                            cartaSeleccionadaDos?.setContentDescription("fallaste!!")
                            handler.postDelayed(Runnable { // Do something after 5s = 5000ms inicio del retardo
                                if(puntuacion >= puntos){
                                    puntuacion -= puntos
                                } else {
                                    puntuacion = 0;
                                }


                                textoPuntuacion?.setText("Puntuación: $puntuacion")
                                //Regresamos las cartas a como estaban antes de voltearlas
                                cartaSeleccionadaDos?.setBackground(tapa)
                                cartaSeleccionadaUno?.setBackground(tapa)
                                cartaSeleccionadaDos?.setContentDescription(cartaDosPos)
                                cartaSeleccionadaUno?.setContentDescription(cartaUnoPos)
                                cartaUno = -1
                                cartaDos = -1
                            }, 3000)   //fin del retardo


                        }  //fin del else de cuando no sean las mismas cartas

                        //Esto debe pasar indistintamente de que sean correctas o no las cartas
                        println("vuelve a intentar WEY : $cartaUno y $cartaDos")








                    } //fin del else if de ser la segunda carta escogida

                } //fin de la funcion OnClickListener***

            }//fin del for *********


        } //fin de la corrutina*****

    } //fin de la funcion OnCreate






} //fin de la clase