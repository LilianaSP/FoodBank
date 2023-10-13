package com.example.foodbankapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DonationStatus : AppCompatActivity() {

    private var estadoSeleccionado: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_status)

        val backButton = findViewById<Button>(R.id.backButton5)
        backButton.setOnClickListener {
            // Recupera el valor actualizado del botón statusButton
            val updatedStatus = (findViewById<Button>(R.id.statusButton)).text.toString()
            var intent = Intent(this, HistorialDonations::class.java)
            // Pasa el valor actualizado como extra
            intent.putExtra("updatedStatus", updatedStatus)
            // También pasa el número de folio y el estado de la donación de regreso a HistorialDonations
            intent.putExtra("numFolio", intent.getStringExtra("numFolio"))
            intent.putExtra("idStatus", intent.getStringExtra("idStatus"))
            startActivity(intent)
            finish()
        }


        // Jala el numFolio desde la activity HistorialDonations
        // Recupera el valor de numFolio pasado como extra
        val numFolio = intent.getStringExtra("numFolio")

        // Encuentra el elemento con el ID idFolio en la vista
        val idFolioTextView = findViewById<TextView>(R.id.idFolio)

        // Establece el valor de numFolio en el elemento idFolio
        idFolioTextView.text = numFolio



        val showPopupButton = findViewById<Button>(R.id.statusButton)

        showPopupButton.setOnClickListener {
            showCustomPopup(showPopupButton)
        }
    }

    private fun showCustomPopup(buttonToUpdate: Button) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.status_pop_up)

        // Obtén una referencia a los botones en el elemento emergente
        val activoButton = dialog.findViewById<Button>(R.id.button)
        val completadoButton = dialog.findViewById<Button>(R.id.button2)
        val canceladoButton = dialog.findViewById<Button>(R.id.button3)

        activoButton.setOnClickListener {
            // Cambia el fondo del botón a activo (lightBlue)
            buttonToUpdate.setBackgroundColor(Color.parseColor("#2D8DE5"))
            // Cambia el texto del botón a "Activa"
            buttonToUpdate.text = "Activa"
            // Actualiza el valor del botón statusButton

            estadoSeleccionado = "Activa"
            val returnIntent = Intent()
            returnIntent.putExtra("estado", estadoSeleccionado)
            setResult(RESULT_OK, returnIntent)

            // Guarda el estado seleccionado en SharedPreferences
            val editor = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE).edit()
            editor.putString("selectedStatus", estadoSeleccionado)
            editor.apply()
            dialog.dismiss()
        }

        completadoButton.setOnClickListener {
            // Cambia el fondo del botón a completado (lightGreen)
            buttonToUpdate.setBackgroundColor(Color.parseColor("#06CB52"))
            // Cambia el texto del botón a "Completada"
            buttonToUpdate.text = "Completada"
            dialog.dismiss() // Cierra el diálogo actual

            // Muestra el segundo diálogo "completed_status_donation"
            val secondDialog = Dialog(this)
            secondDialog.setContentView(R.layout.completed_status_donation)
            val confirmCancelationButton = secondDialog.findViewById<Button>(R.id.TomarFotoButton)


            estadoSeleccionado = "Copmletada"
            val returnIntent = Intent()
            returnIntent.putExtra("estado", estadoSeleccionado)
            setResult(RESULT_OK, returnIntent)

            // Guarda el estado seleccionado en SharedPreferences
            val editor = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE).edit()
            editor.putString("selectedStatus", estadoSeleccionado)
            editor.apply()



            confirmCancelationButton.setOnClickListener {
                // Realiza la acción de confirmar la cancelación aquí
                // Por ejemplo, puedes guardar el motivo de cancelación en una base de datos o realizar la acción deseada.
                secondDialog.dismiss() // Cierra el segundo diálogo
            }

            secondDialog.show()
        }

        canceladoButton.setOnClickListener {
            // Cambia el fondo del botón a cancelado (red)
            buttonToUpdate.setBackgroundColor(Color.parseColor("#DD0E2A"))
            // Cambia el texto del botón a "Cancelada"
            buttonToUpdate.text = "Cancelada"
            dialog.dismiss() // Cierra el diálogo actual

            // Muestra el segundo diálogo "canceled_status_donation"
            val secondDialog = Dialog(this)
            secondDialog.setContentView(R.layout.canceled_status_donationa)
            val confirmCancelationButton = secondDialog.findViewById<Button>(R.id.TomarFotoButton)

            estadoSeleccionado = "Cancelada"
            val returnIntent = Intent()
            returnIntent.putExtra("estado", estadoSeleccionado)
            setResult(RESULT_OK, returnIntent)

            // Guarda el estado seleccionado en SharedPreferences
            val editor = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE).edit()
            editor.putString("selectedStatus", estadoSeleccionado)
            editor.apply()

            confirmCancelationButton.setOnClickListener {
                // Realiza la acción de confirmar la cancelación aquí
                // Aquí se implementa la funcionalidad de la base de datos por si se necesita guardar el mensaje de cancelación
                secondDialog.dismiss() // Cierra el segundo diálogo
            }

            secondDialog.show()
        }

        dialog.show()
    }


}