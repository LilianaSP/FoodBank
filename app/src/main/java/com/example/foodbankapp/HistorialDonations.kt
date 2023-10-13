package com.example.foodbankapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog


class HistorialDonations : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var dialog: BottomSheetDialog // Declarar el diálogo como una propiedad de la actividad
    private lateinit var backgroundSemiTransparent: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_donations)

        // NABAR: >>>>>>>>

        val dashButton: Button = findViewById(R.id.dashButton)
        val view = layoutInflater.inflate(R.layout.dashboardmenu, null)

        dialog = BottomSheetDialog(this)
        dialog.setContentView(view)

        // Obtén una referencia al fondo semi-transparente
        backgroundSemiTransparent = findViewById(R.id.background_dim)

        dashButton.setOnClickListener {
            // Mostrar el diálogo y cambiar el color del fondo semi-transparente
            backgroundSemiTransparent.visibility = View.VISIBLE
            dialog.show()
        }

        // Asigna un oyente para el evento onDismiss del diálogo
        dialog.setOnDismissListener {
            // Oculta el fondo semi-transparente cuando se cierra el diálogo
            backgroundSemiTransparent.visibility = View.INVISIBLE
        }

        //Obtenemos las referencias de los botones del dashboardmenu
        val mainPage = view.findViewById<Button>(R.id.MainPageButton)
        val recaudacionesButton = view.findViewById<Button>(R.id.recaudacionesButton)
        val donationsHistoryButton = view.findViewById<Button>(R.id.HistoryButton)
        val settingButton = view.findViewById<Button>(R.id.ConfigButton)

        mainPage.setOnClickListener {
            // Realiza las acciones necesarias
            var intent = Intent(this, LoggedInActivity::class.java)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }

        recaudacionesButton.setOnClickListener {
            // Realiza las acciones necesarias
            var intent = Intent(this, Donations::class.java)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }

        donationsHistoryButton.setOnClickListener {
            // Realiza las acciones necesarias
            var intent = Intent(this, HistorialDonations::class.java)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }

        settingButton.setOnClickListener {
            // Realiza las acciones necesarias
            var intent = Intent(this, Settings::class.java)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }

        // <<<<<<<<

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
            Donacion(3, "Folio 3", "Status 3", "Nombre 3", "Aliado 3", selectedStatus)
            // Agrega más elementos según tus datos
        )
    }

    data class Donacion(val id: Int, val numFolio: String, val idStatus: String, val idNombre: String, val aliadoStatus: String, var estado: String)
}

