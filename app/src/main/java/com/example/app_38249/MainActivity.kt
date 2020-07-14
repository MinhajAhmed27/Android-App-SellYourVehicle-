package com.example.app_38249

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_up_vehicle.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_loginMain.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

        }
        btn_manageUser.setOnClickListener {
//            startActivity(Intent(this,ManageUserActivity::class.java))

        }



        btn_sellVehicle.setOnClickListener {


            startActivity(Intent(this,LoginActivity::class.java))
//            finish()

//  =============>          after authentication this will be called
//            startActivity(Intent(this,UploadVehicleActivity::class.java))

        }

        btn_searchVehicle.setOnClickListener {
            startActivity(Intent(this,FilterActivity::class.java))
        }

    }
}
