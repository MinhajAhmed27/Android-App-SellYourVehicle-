package com.example.app_38249

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
//
//private lateinit var auth: FirebaseAuth

class ManageUserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_user)

//        auth = FirebaseAuth.getInstance()
//
//        val current_user = auth.currentUser
//
//        if (current_user != null) {
//            if(current_user.isEmailVerified) {
//
//
//
//
//
//            }else{
//                Toast.makeText(
//                    baseContext, "Verify your email address first.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        } else {
//            Toast.makeText(
//                baseContext, "Please Login",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
        val bundle:Bundle? = intent.extras
                val Image = bundle!!.getString("image_intent")
                val Price= bundle.getString("price_intent")
                val Model= bundle.getString("model_intent")
                price_detail.setText(Price)
                model_detail.setText(Model)
                Picasso.get().load(Image).into(image_detail)



    }
}