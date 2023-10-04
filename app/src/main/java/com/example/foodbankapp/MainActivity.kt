package com.example.foodbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtenemos nuestro botón de LogIn por id
        var loginButton = findViewById<Button>(R.id.LogInButton)
        var signupButton = findViewById<Button>(R.id.SignUpButton)

        // Creamos el cambio de activity para la activity_log_in
        loginButton.setOnClickListener{
            var intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }

        signupButton.setOnClickListener{
            var intent = Intent(this, Donations::class.java)
            startActivity(intent)
            finish()
        }
    }
}