package com.example.foodbankapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView


class HistorialDonations : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_donations)


        val scrollView = findViewById<LinearLayout>(R.id.linearLayoutInScrollView)
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        val elementosDeDonacion: List<Donacion> = obtenerDatosDeBackend()

        var donationPosition: Int = -1
        val someActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val estadoActualizado = data?.getStringExtra("estado")
                val donacionId = data?.getIntExtra("donacionId", -1)
                if (estadoActualizado != null && donacionId != -1) {
                    // Actualiza el estado en la lista elementosDeDonacion
                    for (donacion in elementosDeDonacion) {
                        if (donacion.id == donacionId) {
                            donacion.estado = estadoActualizado
                            break
                        }
                    }
                }
            }
        }

        for (donacion in elementosDeDonacion) {
            // Infla el diseño del elemento de donación
            val itemView = LayoutInflater.from(this).inflate(R.layout.recylcler_view_item, null, false)

            val numFolioTextView = itemView.findViewById<TextView>(R.id.NumFolio)
            numFolioTextView.text = donacion.numFolio

            val idstatusTextView = itemView.findViewById<TextView>(R.id.idstatus)
            idstatusTextView.text = donacion.estado

            val idNombreTextView = itemView.findViewById<TextView>(R.id.idNombre)
            idNombreTextView.text = donacion.idNombre

            val aliadoStatusTextView = itemView.findViewById<TextView>(R.id.aliadoStatus)
            aliadoStatusTextView.text = donacion.aliadoStatus

            val folioButton = itemView.findViewById<Button>(R.id.FolioButton)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 24)
            itemView.layoutParams = layoutParams
            scrollView.addView(itemView)

            folioButton.setOnClickListener {
                val intent = Intent(this, DonationStatus::class.java)
                intent.putExtra("numFolio", donacion.numFolio)
                intent.putExtra("idStatus", donacion.estado)
                intent.putExtra("donacionId", donacion.id) // Pasar el identificador único
                donationPosition = donacion.id
                someActivityResultLauncher.launch(intent)
            }
        }
    }

    private fun obtenerDatosDeBackend(): List<Donacion> {
        // Implementa la lógica para obtener los datos de backend
        val selectedStatus = sharedPreferences.getString("selectedStatus", "Activo") ?: "Activo"
        return listOf(
            Donacion(1, "Folio 1", "Status 1", "Nombre 1", "Aliado 1", selectedStatus),
            Donacion(2, "Folio 2", "Status 2", "Nombre 2", "Aliado 2", selectedStatus),
            Donacion(3, "Folio 2", "Status 2", "Nombre 2", "Aliado 2", selectedStatus)
            // Agrega más elementos según tus datos
        )
    }

    data class Donacion(val id: Int, val numFolio: String, val idStatus: String, val idNombre: String, val aliadoStatus: String, var estado: String)
}


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

