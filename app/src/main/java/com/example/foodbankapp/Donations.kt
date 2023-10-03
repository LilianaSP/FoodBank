package com.example.foodbankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu

class Donations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)

        // Objetenemos el botón para desplegar los meses
        val btnMes = findViewById<Button>(R.id.mont_donationms)

        // Create a PopupMenu
        val popupMenu = PopupMenu(this, btnMes)

        // Inflate the menu with the month items
        popupMenu.menuInflater.inflate(R.menu.donations_month_menu, popupMenu.menu)

        // Set a listener for menu item clicks
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

        // Set a click listener for the button to show the Popup Menu
        btnMes.setOnClickListener { view: View ->
            popupMenu.show()
        }
    }
}