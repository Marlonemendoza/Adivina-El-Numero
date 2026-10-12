package com.example.adivinaelnumero

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Actividad de bienvenida / Pantalla principal de la aplicación.
 *
 * Funciona como landing page del juego, mostrando un fondo animado y un botón
 * de navegación hacia la configuración del usuario ([InicioUsuarioActivity]).
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración y reproducción del fondo con transición de gradiente animado
        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)
        val animationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1500)
        animationDrawable.setExitFadeDuration(1500)
        animationDrawable.start()

        // Configuración de márgenes para la compatibilidad con pantallas Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón para iniciar el flujo de la aplicación hacia la pantalla de ingreso de usuario
        val btnEmpezar: Button = findViewById(R.id.btnEmpezar)
        btnEmpezar.setOnClickListener {
            val intent = Intent(this, InicioUsuarioActivity::class.java)
            startActivity(intent)
        }
    }
}