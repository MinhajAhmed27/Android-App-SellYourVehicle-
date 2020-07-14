package com.example.app_38249

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        btn_submit.setOnClickListener {
            Login()
        }
    }

    private fun Login() {
        if (username.text.toString().isEmpty()) {
            username.error = "Enter email"
            username.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Enter password"
            password.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            username.error = "Enter valid email"
            username.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val current_user = auth.currentUser
        updateUI(current_user)
    }

    fun updateUI(current_user:FirebaseUser?){

        if (current_user != null) {
            if(current_user.isEmailVerified) {
                Toast.makeText(
                    baseContext, "Congrats. Your are now logged-in!.",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, UploadVehicleActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Verify your email address first.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "SignUp first.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}