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

        // NOTA MUY IMPORTANTE: Desarrollé el front con la idea (money, meds y food)
        // Pero después de la última junta con Brenda (FoodBank) decidió que cambiaramos
        // de Money --> Clothes (ropa), sin embargo tod_o lo tengo implementado con
        // la palabra MONEY, pero para avisarles que siemore que vean money ahora es ropa
        // SOLAMENTE en el UI si lo cambié para que en la screen diga ropa :)

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
            // Actualizar ProgressBar de Money (now clothes)
            progressBarMoney.progress = selectedMonthData.currentProgressMoney
            progressTextMoney.text = "${selectedMonthData.currentProgressMoney} Pzs"
            goalTextMoney.text = "Objetivo: ${selectedMonthData.goalMoney} Pzs"

            // Actualizar ProgressBar de Meds
            progressBarMeds.progress = selectedMonthData.currentProgressMeds
            progressTextMeds.text = "${selectedMonthData.currentProgressMeds} Pzs"
            goalTextMeds.text = "Objetivo: ${selectedMonthData.goalMeds} Pzs"

            // Actualizar ProgressBar de Food
            progressBarFood.progress = selectedMonthData.currentProgressFood
            progressTextFood.text = "${selectedMonthData.currentProgressFood} Kg"
            goalTextFood.text = "Objetivo: ${selectedMonthData.goalFood} Kg"
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

        // obtener el text del mes para cambiar al nombre del mes seleccionado
        val btnMesText = findViewById<TextView>(R.id.textMesDonacion)

        // LISTENER
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.enero -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["enero"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.febrero -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["febrero"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.marzo -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["marzo"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.abril -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["abril"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.mayo -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["mayo"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.junio -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["junio"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.julio -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["julio"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.agosto -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["agosto"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.septiembre -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["septiembre"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.octubre -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["octubre"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.noviembre -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["noviembre"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }R.id.diciembre -> {
                    val selectedMonthName = menuItem.title.toString()
                    btnMesText.text = selectedMonthName
                    val selectedMonthData = monthData["diciembre"]
                    if (selectedMonthData != null) {
                        animateProgressBar(progressBarMoney, selectedMonthData.currentProgressMoney)
                        animateProgressBar(progressBarMeds, selectedMonthData.currentProgressMeds)
                        animateProgressBar(progressBarFood, selectedMonthData.currentProgressFood)
                        updateProgressBars(selectedMonthData)
                    }
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
    }
}