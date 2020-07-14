package com.example.app_38249

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_login.*

class DetailActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        auth = FirebaseAuth.getInstance()


        val bundle:Bundle? = intent.extras

        val Image = bundle!!.getString("image_intent")

        val Price= bundle.getString("price_intent")
        val Make= bundle.getString("make_intent")
        val Year= bundle.getString("year_intent")
        val Model= bundle.getString("model_intent")
        val Km= bundle.getString("km_intent")
        val Fuel= bundle.getString("fuel_intent")
        val Transmission= bundle.getString("trans_intent")


        price_detail.setText(Price)
        make_detail.setText(Make)
        model_detail.setText(Model)
        year_detail.setText(Year)
        km_detail.setText(Km)
        fuel_detail.setText(Fuel)
        trans_detail.setText(Transmission)


        Picasso.get().load(Image).into(image_detail)


        btn_favorite.setOnClickListener {

            val current_user = auth.currentUser

            if (current_user != null) {
                if(current_user.isEmailVerified) {
                    Toast.makeText(
                        baseContext, "Add to Favorite!.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this,ManageUserActivity::class.java)
                    intent.putExtra("image_intent",Image)
                    intent.putExtra("make_intent",Make)
                    intent.putExtra("model_intent",Model)
                    intent.putExtra("price_intent",Price)

                    startActivity(intent)

                    btn_favorite.setBackgroundColor(Color.rgb(230, 83, 100))
                }else{
                    Toast.makeText(
                        baseContext, "Verify your email address first.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    baseContext, "Please Login",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}
