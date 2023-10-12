package com.example.foodbankapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtenemos nuestro botón de LogIn por id
        var loginButton = findViewById<Button>(R.id.LogInButton)
        var signupButton = findViewById<Button>(R.id.SignUpButton)

        // Creamos el cambio de activity para la activity_log_in
        loginButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signupButton.setOnClickListener{

            var intent = Intent(this, EditProfile::class.java)


            var intent = Intent(this, LoggedInActivity::class.java)


            startActivity(intent)
            finish()
        }
    }
}