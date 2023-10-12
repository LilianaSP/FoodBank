package com.example.foodbankapp

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DonationStatus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_status)

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
            dialog.dismiss() // Cierra el diálogo actual
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
            val confirmCancelationButton = secondDialog.findViewById<Button>(R.id.canceledDonationButton)

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
            val confirmCancelationButton = secondDialog.findViewById<Button>(R.id.canceledDonationButton)

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