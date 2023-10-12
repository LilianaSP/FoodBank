package com.example.foodbankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HistorialDonations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_donations)

        // Declaración de variables y asignación de valores de ejemplo
        val nombreTextView = findViewById<TextView>(R.id.nombreTextView)
        val aliadoTextView = findViewById<TextView>(R.id.aliadoTextView)
        val correoTextView = findViewById<TextView>(R.id.correoTextView)
        val donacionesTextView = findViewById<TextView>(R.id.donacionesTextView)
        val telefonoTextView = findViewById<TextView>(R.id.telefonoTextView)
        val mensajeTextView = findViewById<TextView>(R.id.mensajeTextView)



        // Asignación de valores de ejemplo
        val nombreCompleto = "Emilio Berber"
        val esAliado = "Sí" // Puedes usar "Sí" o "No" según la información real.
        val correo = "emilioberber@hotmail.com"
        val tipoDonacion = "Food"
        val telefono = "123 456 78 90"
        val mensaje = "He donado 25 paquetes de fresas"

        // Asignación de valores a los TextViews
        nombreTextView.text = nombreCompleto
        aliadoTextView.text = esAliado
        correoTextView.text = correo
        donacionesTextView.text = tipoDonacion
        telefonoTextView.text = telefono
        mensajeTextView.text = mensaje

    }
}
