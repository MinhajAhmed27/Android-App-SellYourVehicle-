package com.example.app_38249

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

//        var make = make_path.text
//        var model = model_path.text
//        var year = year_path.text
//        var trans = trans_path.text
//
//        var make_value = make_value.text
//        var model_value = model_value.text
//        var year_value = year_value.text
//        var trans_value = trans_value.text


        btn_filter.setOnClickListener {
            startActivity(Intent(this,ShowActivity::class.java))
//            val intent = Intent(this,ShowActivity::class.java)
//                intent.putExtra("make_intent",make)
//                intent.putExtra("makeV_intent",make_value)
//
////                intent.putExtra("year_intent",year)
////                intent.putExtra("yearV_intent",year_value)
////
////                intent.putExtra("model_intent",model)
////                intent.putExtra("modelV_intent",model_value)
////
//////            intent.putExtra("fuel_intent",model.fuel)
//////            intent.putExtra("model_intent",model.model)
////            intent.putExtra("trans_intent",trans)
//            intent.putExtra("transV_intent",trans)
//            startActivity(intent)

        }


    }
}
