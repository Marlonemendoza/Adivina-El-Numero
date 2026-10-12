package com.example.adivinaelnumero

// Importaciones de clases Android Framework, UI y Utilidades de Kotlin
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Integer.parseInt
import kotlin.random.Random
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Actividad principal del juego "Adivina el Número".
 *
 * Se encarga de gestionar la lógica del juego, la interacción con el usuario,
 * el conteo de intentos, la generación del número aleatorio en un rango definido,
 * las pistas dinámicas y las animaciones/efectos visuales de victoria.
 */
class JuegoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el modo borde a borde (Edge-to-Edge) para la UI
        enableEdgeToEdge()
        setContentView(R.layout.activity_juego)

        // ==========================================
        // CONFIGURACIÓN DE ANIMACIÓN DE FONDO
        // ==========================================
        // Obtención de la vista principal para extraer su fondo animado (AnimationDrawable)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)
        val animationDrawable = constraintLayout.background as AnimationDrawable

        // Configuración de las transiciones (suavizado/desvanecimiento) de la animación
        animationDrawable.setEnterFadeDuration(1500)
        animationDrawable.setExitFadeDuration(1500)

        // Inicia la animación del fondo
        animationDrawable.start()

        // ==========================================
        // CONFIGURACIÓN DEL JUEGO Y DATOS RECIBIDOS
        // ==========================================
        // Recupera el límite superior del rango enviado desde la actividad anterior
        val rangoRecibido = intent.getIntExtra("Rango", 0)

        // Genera un número aleatorio entre 1 y el rango recibido (inclusive)
        val numeroAleatorio = (1..rangoRecibido).random()

        // Muestra en pantalla el rango de juego
        val rango: TextView = findViewById(R.id.tvRango)
        rango.setText("Rango de Adivinanza: 1..." + rangoRecibido)

        // Recupera el nombre del jugador enviado desde la actividad InicioUsuarioActivity
        val nombreRecibido = intent.getStringExtra("NOMBRE_JUGADOR") ?: "nombreJugador"

        // Muestra el nombre del jugador en el encabezado
        val tvNombreTitulo: TextView = findViewById(R.id.tvNombreTitulo)
        tvNombreTitulo.text = "Jugador: $nombreRecibido"

        // Contador para llevar el control de los intentos realizados por el usuario
        var intentos = 0

        // Referencia al botón principal para validar intentos
        val btnAdivinar: Button = findViewById(R.id.btnAdivinar)

        // Ajuste dinámico de márgenes para la barra de estado y de navegación del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ==========================================
        // LÓGICA DE INTERACCIÓN AL HACER CLIC
        // ==========================================
        btnAdivinar.setOnClickListener {
            val etNumero: EditText = findViewById(R.id.etNumero)
            val numeroIngresado = etNumero.text.toString().toIntOrNull()
            val tvPista: TextView = findViewById(R.id.tvPista)
            val tvCantidadIntentos: TextView = findViewById(R.id.tvCantidadIntentos)

            // Efecto visual parpadeante de actualización en el texto de la pista
            lifecycleScope.launch {
                tvPista.visibility = View.GONE
                delay(300) // Pausa breve de 300 ms
                tvPista.visibility = View.VISIBLE
            }

            // ------------------------------------------
            // CASO 1: EL USUARIO ADIVINÓ EL NÚMERO
            // ------------------------------------------
            if (numeroIngresado == numeroAleatorio) {
                // Oculta el teclado táctil para dar espacio a la pantalla de victoria
                ocultarTeclado()
                intentos++ // Incrementa el intento exitoso

                // Obtención de referencias a las vistas para mostrar el resultado y efectos de estrellas
                val cvImagen: CardView = findViewById(R.id.cardView)
                val tvEscribirNumero: TextView = findViewById(R.id.tvEscribirNumero)
                val tvMensaje: TextView = findViewById(R.id.tvMensaje)
                val tvNumeroOculto: TextView = findViewById(R.id.tvNumeroOculto)

                // Elementos decorativos (estrellas)
                val tvEstrellas1: TextView = findViewById(R.id.tvEstrellas1)
                val tvEstrellas2: TextView = findViewById(R.id.tvEstrellas2)
                val tvEstrellas3: TextView = findViewById(R.id.tvEstrellas3)
                val tvEstrellas4: TextView = findViewById(R.id.tvEstrellas4)
                val tvEstrellas5: TextView = findViewById(R.id.tvEstrellas5)
                val tvEstrellasLaterales1: TextView = findViewById(R.id.tvEstrellasLaterales1)
                val tvEstrellasLaterales2: TextView = findViewById(R.id.tvEstrellasLaterales2)
                val tvEstrellasLaterales3: TextView = findViewById(R.id.tvEstrellasLaterales3)
                val tvEstrellasLaterales4: TextView = findViewById(R.id.tvEstrellasLaterales4)
                val tvEstrellasLaterales5: TextView = findViewById(R.id.tvEstrellasLaterales5)
                val tvEstrellasLaterales6: TextView = findViewById(R.id.tvEstrellasLaterales6)
                val tvEstrellasLaterales7: TextView = findViewById(R.id.tvEstrellasLaterales7)
                val tvEstrellasLaterales8: TextView = findViewById(R.id.tvEstrellasLaterales8)

                // Transición asíncrona hacia la pantalla de celebración/victoria
                lifecycleScope.launch {
                    // Oculta elementos de ingreso de texto y controles de juego
                    tvEscribirNumero.visibility = View.GONE
                    etNumero.visibility = View.GONE
                    cvImagen.visibility = View.GONE
                    btnAdivinar.visibility = View.GONE

                    delay(300) // Espera antes de revelar la pantalla final

                    // Acelera la velocidad del fondo animado para dar efecto de victoria
                    animationDrawable.setEnterFadeDuration(200)
                    animationDrawable.setExitFadeDuration(200)
                    animationDrawable.start()

                    // Muestra el número adivinado, los intentos y la decoración con estrellas
                    tvNumeroOculto.visibility = View.VISIBLE
                    tvNumeroOculto.setText("" + numeroAleatorio)
                    tvCantidadIntentos.visibility = View.VISIBLE
                    tvEstrellas1.visibility = View.VISIBLE
                    tvEstrellas2.visibility = View.VISIBLE
                    tvEstrellas3.visibility = View.VISIBLE
                    tvEstrellas4.visibility = View.VISIBLE
                    tvEstrellas5.visibility = View.VISIBLE
                    tvEstrellasLaterales1.visibility = View.VISIBLE
                    tvEstrellasLaterales2.visibility = View.VISIBLE
                    tvEstrellasLaterales3.visibility = View.VISIBLE
                    tvEstrellasLaterales4.visibility = View.VISIBLE
                    tvEstrellasLaterales5.visibility = View.VISIBLE
                    tvEstrellasLaterales6.visibility = View.VISIBLE
                    tvEstrellasLaterales7.visibility = View.VISIBLE
                    tvEstrellasLaterales8.visibility = View.VISIBLE
                    tvMensaje.setText("Número oculto:")
                }

                // Actualiza los textos con mensajes de felicitación
                tvPista.setText("¡⭐⭐⭐Adivinaste el numero⭐⭐⭐!")
                etNumero.setText("⭐⭐⭐¡Adivinaste el numero!⭐⭐⭐")
                tvCantidadIntentos.setText("Intentos: " + intentos)

                // ------------------------------------------
                // CASO 2: EL USUARIO NO HA ADIVINADO
                // ------------------------------------------
            } else {
                intentos++ // Incrementa el contador de intentos fallidos

                if (numeroIngresado == null) {
                    // El usuario no ingresó ningún número o introdujo texto no válido
                } else if (numeroIngresado < numeroAleatorio) {
                    tvPista.setText("El número oculto es mayor!")
                } else if (numeroIngresado > numeroAleatorio) {
                    tvPista.setText("El número oculto es menor!")
                }
            }
        }
    }

    /**
     * Función de extensión para la clase [Activity] que oculta el teclado virtual en pantalla.
     */
    fun Activity.ocultarTeclado() {
        val view = currentFocus ?: View(this)
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}