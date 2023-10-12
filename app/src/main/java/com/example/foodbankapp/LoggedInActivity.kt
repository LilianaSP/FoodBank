package com.example.foodbankapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class LoggedInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        // Obtenemos la refrerencia de nuestro archivo dashboardmenu xml donde se implementa el menu desplegable
        val dashButton: Button = findViewById(R.id.dashButton)
        val view = layoutInflater.inflate(R.layout.dashboardmenu, null)


        dashButton.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)


            // Modificar el background de acuerdo con el popup para que cuando salga
            val backgroundSemiTransparent = findViewById<FrameLayout>(R.id.background_dim)
            backgroundSemiTransparent.visibility = View.VISIBLE
            dialog.setOnDismissListener{
                backgroundSemiTransparent.visibility = View.INVISIBLE
            }

            //Obtenemos las referencias de los botones del dashboardmenu
            val mainPage = view.findViewById<Button>(R.id.MainPageButton)
            val recaudacionesButton = view.findViewById<Button>(R.id.recaudacionesButton)
            val donationsHistoryButton = view.findViewById<Button>(R.id.HistoryButton)
            val settingButton = view.findViewById<Button>(R.id.ConfigButton)

            mainPage.setOnClickListener {
                val intent = Intent(this, LoggedInActivity::class.java)
                startActivity(intent)
                dialog.dismiss() // Cerrar el menú emergente porque esta es el main page
            }

            recaudacionesButton.setOnClickListener {
                val intent = Intent(this, Donations::class.java)
                startActivity(intent)
                dialog.dismiss() // Cerrar el menú emergente
            }

            donationsHistoryButton.setOnClickListener {
                val intent = Intent(this, HistorialDonations::class.java)
                startActivity(intent)
                dialog.dismiss() // Cerrar el menú emergente
            }

            settingButton.setOnClickListener {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
                dialog.dismiss() // Cerrar el menú emergente
            }



            // Aplica la animación personalizada
            dialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    // Puedes agregar lógica personalizada aquí si lo deseas
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // Aplica la animación personalizada durante el deslizamiento
                    view.translationY = slideOffset * resources.getDimension(R.dimen.bottom_sheet_peek_height)
                }
            })


            dialog.show()
        }


    }
}