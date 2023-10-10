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
import android.view.animation.Animation
import android.view.animation.Transformation

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

        // >>>>>>>>>> MEDS:

        // Obtener referencia al ProgressBar
        val progressBarMeds = findViewById<ProgressBar>(R.id.progressBarMeds)
        // Obtenemos en variables los valores de los números de progreso y objetivo
        val progressTextMeds = findViewById<TextView>(R.id.textViewDonationMeds)
        val goalTextMeds = findViewById<TextView>(R.id.textViewGoalMeds)

        // >>>>>>>>>>> FOOD:
        // Obtener referencia al ProgressBar
        val progressBarFood = findViewById<ProgressBar>(R.id.progressBarFood)
        // Obtenemos en variables los valores de los números de progreso y objetivo
        val progressTextFood = findViewById<TextView>(R.id.textViewDonationFood)
        val goalTextFood = findViewById<TextView>(R.id.textViewGoalFood)

        //********* de manera optimizada
        // Define una clase para representar los datos del mes
        data class MonthData(val currentProgressMoney: Int, val goalMoney: Int,
                             val currentProgressMeds: Int, val goalMeds: Int,
                             val currentProgressFood: Int, val goalFood: Int)

        // DICCIONARIO DE VARIABLES PARA BACK-END (SAM, ANA)!!
        val monthData = mutableMapOf(
            "enero" to MonthData(60, 100, 30, 100, 40, 100),
            "febrero" to MonthData(30, 100, 20, 100, 100, 100),
            "marzo" to MonthData(10, 100, 30, 100, 40, 100),
            "abril" to MonthData(20, 100, 90, 100, 100, 100),
            "mayo" to MonthData(100, 100, 10, 100, 40, 100),
            "junio" to MonthData(30, 100, 80, 100, 100, 100),
            "julio" to MonthData(50, 100, 40, 100, 40, 100),
            "agosto" to MonthData(90, 100, 90, 100, 100, 100),
            "septiembre" to MonthData(80, 100, 30, 100, 40, 100),
            "octubre" to MonthData(10, 100, 20, 100, 100, 100),
            "noviembre" to MonthData(20, 100, 10, 100, 100, 100),
            "diciembre" to MonthData(55, 100, 23, 100, 40, 100)

        )

        // función para tomar los valores de progress, goal para después mostrarlos
        fun updateProgressBars(selectedMonthData: MonthData) {
            // Actualizar ProgressBar de Money
            progressBarMoney.progress = selectedMonthData.currentProgressMoney
            progressTextMoney.text = "${selectedMonthData.currentProgressMoney}%"
            goalTextMoney.text = "Objetivo: ${selectedMonthData.goalMoney}%"

            // Actualizar ProgressBar de Meds
            progressBarMeds.progress = selectedMonthData.currentProgressMeds
            progressTextMeds.text = "${selectedMonthData.currentProgressMeds}%"
            goalTextMeds.text = "Objetivo: ${selectedMonthData.goalMeds}%"

            // Actualizar ProgressBar de Food
            progressBarFood.progress = selectedMonthData.currentProgressFood
            progressTextFood.text = "${selectedMonthData.currentProgressFood}%"
            goalTextFood.text = "Objetivo: ${selectedMonthData.goalFood}%"
        }

        // >>>>>>> ANIMATION progress bar
        // clase progress bar animation
        class ProgressBarAnimation(
            private val progressBar: ProgressBar,
            private val from: Int,
            private val to: Int
        ) : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val value = from + (to - from) * interpolatedTime
                progressBar.progress = value.toInt()
            }
        }

        // Función para animar la progress-bar
        fun animateProgressBar(progressBar: ProgressBar, targetProgress: Int) {
            val animation = ProgressBarAnimation(progressBar, progressBar.progress, targetProgress)
            animation.duration = 1000 // Duración de la animación en milisegundos (ajusta según tus preferencias)
            progressBar.startAnimation(animation)
        }

        // LISTENER
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.enero, R.id.febrero, R.id.marzo, R.id.abril, R.id.mayo, R.id.junio,
                R.id.julio, R.id.agosto, R.id.septiembre, R.id.octubre, R.id.noviembre, R.id.diciembre -> {
                    val selectedMonthData = monthData[menuItem.title.toString().toLowerCase()]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.enero -> {
                    val selectedMonthData = monthData["enero"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.febrero -> {
                    val selectedMonthData = monthData["febrero"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.marzo -> {
                    val selectedMonthData = monthData["marzo"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.abril -> {
                    val selectedMonthData = monthData["abril"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.mayo -> {
                    val selectedMonthData = monthData["mayo"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.junio -> {
                    val selectedMonthData = monthData["junio"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.julio -> {
                    val selectedMonthData = monthData["julio"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.agosto -> {
                    val selectedMonthData = monthData["agosto"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.septiembre -> {
                    val selectedMonthData = monthData["septiembre"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.octubre -> {
                    val selectedMonthData = monthData["octubre"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.noviembre -> {
                    val selectedMonthData = monthData["noviembre"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.diciembre -> {
                    val selectedMonthData = monthData["diciembre"]
                    if (selectedMonthData != null) {
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
    }
}