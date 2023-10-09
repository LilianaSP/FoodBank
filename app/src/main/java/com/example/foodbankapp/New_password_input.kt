package com.example.foodbankapp



import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class New_password_input : AppCompatActivity() {

    lateinit var passwordInput: EditText
    lateinit var confirmPasswordInput: EditText
    lateinit var passwordMatchCondition: TextView
    private lateinit var charactersNumCondition: TextView
    private lateinit var mayusMinCondition: TextView
    private lateinit var specialCharCondition: TextView
    private lateinit var containsNumCondition: TextView

    val lightGreenColor = Color.parseColor("#06CB52")
    val defaultColor = Color.parseColor("#FA3C1B")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password_input)

        passwordInput = findViewById(R.id.password_input)
        confirmPasswordInput = findViewById(R.id.password_input2)
        passwordMatchCondition = findViewById(R.id.condition)
        charactersNumCondition = findViewById(R.id.condition1)
        mayusMinCondition = findViewById(R.id.condition2)
        specialCharCondition = findViewById(R.id.condition3)
        containsNumCondition = findViewById(R.id.condition4)

        passwordInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePassword()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar
            }
        })

        val resetPasswordButton = findViewById<Button>(R.id.reestablacerPasswordButton)

        resetPasswordButton.setOnClickListener {
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (isPasswordValid(password, confirmPassword)) {
                Toast.makeText(this, "Contraseña restablecida", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Revisa los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isPasswordValid(password: String, confirmPassword: String): Boolean {
        val isValidLength = password.length >= 8
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val hasDigit = password.any { it.isDigit() }

        return isValidLength && hasUpperCase && hasLowerCase && hasSpecialChar && hasDigit && password == confirmPassword
    }

    fun validatePassword() {
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (password == confirmPassword) {
            setConditionToLightGreen(passwordMatchCondition)
        } else {
            resetConditionColor(passwordMatchCondition)
        }

        if (password.length >= 8) {
            setConditionToLightGreen(charactersNumCondition)
        } else {
            resetConditionColor(charactersNumCondition)
        }

        if (password.any { it.isUpperCase() } && password.any { it.isLowerCase() }) {
            setConditionToLightGreen(mayusMinCondition)
        } else {
            resetConditionColor(mayusMinCondition)
        }

        if (password.any { !it.isLetterOrDigit() }) {
            setConditionToLightGreen(specialCharCondition)
        } else {
            resetConditionColor(specialCharCondition)
        }

        if (password.any { it.isDigit() }) {
            setConditionToLightGreen(containsNumCondition)
        } else {
            resetConditionColor(containsNumCondition)
        }
    }

    fun setConditionToLightGreen(conditionView: TextView) {
        conditionView.setTextColor(lightGreenColor)
    }

    fun resetConditionColor(conditionView: TextView) {
        conditionView.setTextColor(defaultColor)
    }
}

