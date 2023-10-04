package com.example.foodbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.ProgressBar

class Donations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)

        // Obtenemos el botón para desplegar los meses
        val btnMes = findViewById<Button>(R.id.mont_donationms)
        // obtenemos el botón para el settings ...:
        val btnSettDonation = findViewById<Button>(R.id.dontations_navbar)

        // Create a PopupMenu for months
        val popupMenu = PopupMenu(this, btnMes)
        // Create a PopupMenu for settings
        val settingsPopupMenu = PopupMenu(this, btnSettDonation)

        // Inflate the menu with the month items
        popupMenu.menuInflater.inflate(R.menu.donations_month_menu, popupMenu.menu)
        // Inflate the settings menu
        settingsPopupMenu.menuInflater.inflate(R.menu.donations_settings_menu, settingsPopupMenu.menu)

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

        // Set a click listener for the button to show the Popup Menu MONTHS
        btnMes.setOnClickListener { view: View ->
            popupMenu.show()
        }
        // Set a click listener for the button to show the Popup Menu SETTINGS
        btnSettDonation.setOnClickListener { view: View ->
            popupMenu.show()
        }

        // ************ Progress bars
        // MONEY:
        // Obtener referencia al ProgressBar
        val progressBarMoney = findViewById<ProgressBar>(R.id.progressBarMoney)

        // Configurar el valor máximo y la cantidad acumulada
        val maxValueMon = 100 // Valor máximo deseado
        val currentProgressMon = 10 // Valor actual deseado

        // Asignar los valores al ProgressBar
        progressBarMoney.max = maxValueMon
        progressBarMoney.progress = currentProgressMon

        // MEDS:
        // Obtener referencia al ProgressBar
        val progressBarMeds = findViewById<ProgressBar>(R.id.progressBarMeds)

        // Configurar el valor máximo y la cantidad acumulada
        val maxValueMeds = 100 // Valor máximo deseado
        val currentProgressMeds = 80 // Valor actual deseado

        // Asignar los valores al ProgressBar
        progressBarMeds.max = maxValueMeds
        progressBarMeds.progress = currentProgressMeds

        // FOOD:
        val progressBarFood = findViewById<ProgressBar>(R.id.progressBarFood)

        // Configurar el valor máximo y la cantidad acumulada
        val maxValueFood = 100 // Valor máximo deseado
        val currentProgressFood = 30 // Valor actual deseado

        // Asignar los valores al ProgressBar
        progressBarFood.max = maxValueFood
        progressBarFood.progress = currentProgressFood
    }
}