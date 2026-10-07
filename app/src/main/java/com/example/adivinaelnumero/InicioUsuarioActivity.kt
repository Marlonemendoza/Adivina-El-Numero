package com.example.adivinaelnumero

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Pantalla para la captura de datos del jugador.
 *
 * Permite ingresar el nombre del usuario y definir el rango máximo para el juego.
 * Valida que los campos no estén vacíos o en cero antes de pasar a la pantalla del juego ([JuegoActivity]).
 */
class InicioUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio_usuario)

        // Configuración y ejecución de la animación del fondo
        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)
        val animationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1500)
        animationDrawable.setExitFadeDuration(1500)
        animationDrawable.start()

        // Ajuste dinámico de insets para evitar solapamientos con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los componentes de la interfaz de usuario
        val etNombreJugador: EditText = findViewById(R.id.etNombreJugador)
        val etRango: EditText = findViewById(R.id.etRango)
        val btnJugar: Button = findViewById(R.id.btnJugar)

        // Escuchador de eventos para el botón de inicio del juego
        btnJugar.setOnClickListener {
            val nombreJugador = etNombreJugador.text.toString().trim()
            val rango = etRango.text.toString().trim().toIntOrNull() ?: 0

            // Validaciones de los inputs del usuario
            when {
                nombreJugador.isBlank() -> {
                    mostrarAlerta("Nombre vacío", "Debes ingresar algún nombre!")
                }
                rango == 0 -> {
                    mostrarAlerta(
                        "Sin rango de número",
                        "Debe ingresar el rango de adivinanza\nEjemplo: si ingresa 100\nVa de (1...100)!"
                    )
                }
                else -> {
                    // Envío de datos a JuegoActivity e inicio de la pantalla de juego
                    val intent = Intent(this, JuegoActivity::class.java).apply {
                        putExtra("NOMBRE_JUGADOR", nombreJugador)
                        putExtra("Rango", rango)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    /**
     * Muestra un diálogo de alerta de error/advertencia simple al usuario.
     */
    private fun mostrarAlerta(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}