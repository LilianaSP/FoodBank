package com.example.foodbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        // Obtenemos los campos de texto que va a ingresar el usuario y los guardamos en variables
        var backButton = findViewById<Button>(R.id.backButton)
        // Creamos la funcionalidad del back button
        backButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Definimos la variable del sign up button así como su funcionalidad de cambio de activity
        var signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener{
            var intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }


        // Definomos las variables en las cuales se va a almacenar la información de la base datos
        var name_input = findViewById<EditText>(R.id.first_name_input)
        var last_name_input = findViewById<EditText>(R.id.last_name_input)
        var job_type_input = findViewById<EditText>(R.id.job_type)
        var email_input = findViewById<EditText>(R.id.email_Input)
        var password_input = findViewById<EditText>(R.id.password_input)
        var confirm_passowrd_input = findViewById<EditText>(R.id.confirm_password)

        // Estas son las variables que van a utilizar para la base de datos, ya que aquí ya se pasan a strings
        val name = name_input.text.toString()
        val lastName = last_name_input.textScaleX.toString()
        val jobType = job_type_input.text.toString()
        val email = email_input.text.toString()
        val password = password_input.text.toString()
        val confirmPassword = confirm_passowrd_input.text.toString()



    }
}