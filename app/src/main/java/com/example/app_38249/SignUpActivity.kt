package com.example.app_38249

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        btn_signup_submit_.setOnClickListener {
            signUp()
        }

    }

    private fun signUp() {
        if (signup_username.text.toString().isEmpty()) {
            signup_username.error = "Enter your email"
            signup_username.requestFocus()
            return
        }

        if (signup_password.text.toString().isEmpty()) {
            signup_password.error = "Enter your password"
            signup_password.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(signup_username.text.toString()).matches()) {
            signup_username.error = "Plz Enter valid email"
            signup_username.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(signup_username.text.toString(), signup_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(baseContext, "SignUp failed. Try again later.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
