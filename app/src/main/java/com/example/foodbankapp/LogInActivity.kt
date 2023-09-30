package com.example.foodbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        auth=FirebaseAuth.getInstance()

        // Obtenemos nuestro botón de LogIn por id
        var loginButton = findViewById<Button>(R.id.LogInButton)

        //Obtenemos nuestro email input de nuestro UI
        var emailInput = findViewById<EditText>(R.id.email_Input)
        var passwordInput = findViewById<EditText>(R.id.password_Input)

        // Definimos variable y creamos la funcionaldiad de reset password
        var resetPassword = findViewById<TextView>(R.id.Restablecer_password)
        // Creamos la función para el cmabio de activity para la activity de reestablecer la contraseña
        resetPassword.setOnClickListener{
            var intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
            finish()
        }


        var backButton = findViewById<Button>(R.id.backButton)
        // Creamos la función para el cambio de activity para la anterior con el back button
        backButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }





        //Creamos la funcionalidad del botón para el cambio de activity y la programación con la base de datos
         loginButton.setOnClickListener{
            if (checking())
            {
                // convertirmos nuestras entrads de texto en string estas son las variables que se van a utilizar para llamar a la función de "auth.signInWithEmailAndPassword(email,password)"
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                Log.d("AQUI MERO",email)
                Log.d("AQUI MERO",password)

                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        task->
                        if(task.isSuccessful)
                        {
                        // Aquí es donde si el inicio de sesión fue exitoso, se cambia a la activity de logged In
                            var intent = Intent(this, Dashboard::class.java)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            Toast.makeText(this, "Check your credentials!", Toast.LENGTH_LONG).show()

                        }
                    }

            }
            else
            {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()
            }
        }



    }

    // Creamos nuestra función para comprobar si se trata de datos vación o no
    private fun checking():Boolean
    {
        var emailInput = findViewById<EditText>(R.id.email_Input)
        var passwordInput = findViewById<EditText>(R.id.password_Input)

        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if(email.trim{it<=' '}.isNotEmpty() && password.trim{it<=' '}.isNotEmpty())
        {
            return true
        }
        return false
    }
}
