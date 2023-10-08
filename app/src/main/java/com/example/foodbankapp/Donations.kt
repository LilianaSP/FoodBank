package com.example.foodbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class Donations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)

        // ******** BUTTONS POPUP MENUS ***************

        // >>>> MONTH MENU:
        // Obtenemos el botón para desplegar los meses:
        val btnMes = findViewById<Button>(R.id.mont_donationms)

        // Create a PopupMenu for months
        val popupMenu = PopupMenu(this, btnMes)

        // Inflate the menu with the month items
        popupMenu.menuInflater.inflate(R.menu.donations_month_menu, popupMenu.menu)

        // Set a listener for menu item clicks MONTHS
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            // Handle user selection
            when (menuItem.itemId) {
                R.id.enero -> {
                    // Handle January selection
                    return@setOnMenuItemClickListener true
                }
                // Add cases for the other months
                // ...
                else -> return@setOnMenuItemClickListener false
            }
        }



        // Set a click listener for the button to show the Popup Menu MONTHS
        btnMes.setOnClickListener { view: View ->
            popupMenu.show()
        }


        // >>>> SETTINGS MENU:
        // obtenemos el botón para el settings (...):
        val btnSettDonation = findViewById<Button>(R.id.dontations_navbar)

        // Create a PopupMenu for settings
        val settingsPopupMenu = PopupMenu(this, btnSettDonation)

        // Inflate the settings menu
        settingsPopupMenu.menuInflater.inflate(R.menu.donations_settings_menu, settingsPopupMenu.menu)

        // Set a listener for settings menu item clicks SETTINGS
        settingsPopupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            // Handle user selection for settings
            when (menuItem.itemId) {
                R.id.historial_donaciones -> {
                    // Handle the "Historial de donaciones" selection
                    val intent = Intent(this, HistorialDonations::class.java)
                    startActivity(intent)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }


        // Set a click listener for the button to show the Popup Menu SETTINGS
        btnSettDonation.setOnClickListener { view: View ->
            settingsPopupMenu.show()
        }

        // ************ Progress bars **************
        // >>>>>>>>> MONEY:

        // Obtener referencia al ProgressBar
        val progressBarMoney = findViewById<ProgressBar>(R.id.progressBarMoney)
        // Obtenemos en variables los valores de los números de progreso y objetivo
        val progressTextMoney = findViewById<TextView>(R.id.textViewDonationMoney)
        val goalTextMoney = findViewById<TextView>(R.id.textViewGoalMoney)

        // Configurar la cantidad acumulada --> Valor actual
        val currentProgressMon =60 // *VARIABLE PARA BACKEND!!

        // Asignar los valores al ProgressBar
        progressBarMoney.progress = currentProgressMon

        // Establece el texto del progreso actual en el TextView
        progressTextMoney.text = "$currentProgressMon%"

        // Define el objetivo (puedes cambiar este valor según tus necesidades)
        val goalMoney = 100 //**VARIABLE PARA BACKEND!!

        // Establece el texto del objetivo en el TextView
        goalTextMoney.text = "Objetivo: $goalMoney%"

        // >>>>>>>>>> MEDS:

        // Obtener referencia al ProgressBar
        val progressBarMeds = findViewById<ProgressBar>(R.id.progressBarMeds)
        // Obtenemos en variables los valores de los números de progreso y objetivo
        val progressTextMeds = findViewById<TextView>(R.id.textViewDonationMeds)
        val goalTextMeds = findViewById<TextView>(R.id.textViewGoalMeds)

        // Configurar la cantidad acumulada --> Valor actual
        val currentProgressMeds = 30 // ** VARIABLE PARA BACKEND!!

        // Asignar los valores al ProgressBar
        progressBarMeds.progress = currentProgressMeds

        // Establece el texto del progreso actual en el TextView
        progressTextMeds.text = "$currentProgressMeds%"

        // Define el objetivo (puedes cambiar este valor según tus necesidades)
        val goalMeds = 100 //** Variables para BACKEND!!

        // Establece el texto del objetivo en el TextView
        goalTextMeds.text = "Objetivo: $goalMeds%"

        // >>>>>>>>>>> FOOD:
        // Obtener referencia al ProgressBar
        val progressBarFood = findViewById<ProgressBar>(R.id.progressBarFood)
        // Obtenemos en variables los valores de los números de progreso y objetivo
        val progressTextFood = findViewById<TextView>(R.id.textViewDonationFood)
        val goalTextFood = findViewById<TextView>(R.id.textViewGoalFood)

        // Configurar la cantidad acumulada --> Valor actual
        val currentProgressFood = 40 // ** VARIABLES PARA BACKEND!!

        // Asignar los valores al ProgressBar
        progressBarFood.progress = currentProgressFood

        // Establece el texto del progreso actual en el TextView
        progressTextFood.text = "$currentProgressFood%"

        // Objetivo en %
        val goalFood = 100 //** Variables para BACKEND!!

        // Establece el texto del objetivo en el TextView
        goalTextFood.text = "Objetivo: $goalFood%"
    }
}