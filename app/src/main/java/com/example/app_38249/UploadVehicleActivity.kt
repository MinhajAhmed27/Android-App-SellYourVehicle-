package com.example.app_38249

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_up_vehicle.*
import java.util.UUID.randomUUID


private const val REQUEST_CODE_CAMERA = 123
private const val REQUEST_CODE_GALLERY = 456
val ImageId = "image"+ randomUUID().toString()
var downloadUrl:String = ""
class UploadVehicleActivity : AppCompatActivity() {

    lateinit var storageRef: StorageReference


    var database =FirebaseDatabase.getInstance().getReference().child("Vehicles")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_vehicle)


        storageRef = FirebaseStorage.getInstance().getReference().child(ImageId);

        image_up.setOnClickListener {

            //          Touch FeedBack
            image_up.setOnTouchListener(OnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    image_up.setBackgroundColor(Color.rgb(230, 83, 100))
                    return@OnTouchListener true
                } else if (event.action == MotionEvent.ACTION_UP) {
                    image_up.setBackgroundColor(Color.WHITE)
                    imageText_up.setHint("");
                    return@OnTouchListener true
                }
                false
            })
//                      For Camera
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePicture.resolveActivity(this.packageManager)!=null) {
                startActivityForResult(takePicture, REQUEST_CODE_CAMERA)
            }else{
                Toast.makeText(this,"Unable to open camera",Toast.LENGTH_LONG).show()
            }
        }

        btn_gallery_up.setOnClickListener {
            //          For Gallery
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, REQUEST_CODE_GALLERY)
        }

        var vehicleType :String=""
        radioGroup_up.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_car_up){
                vehicleType="car"
            }
            if (checkedId == R.id.radio_bike_up){
                vehicleType="bike"
            }
        }
        var transmissionType :String=""
        radioGroup2_up.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_auto_up){
                vehicleType="auto"
            }
            if (checkedId == R.id.radio_manual_up){
                vehicleType="manual"
            }
        }

        btn_postAd.setOnClickListener {

            var make = make_up.text.toString();
            var model=model_up.text.toString();
            var year=year_up.text.toString()
            var km=km_up.text.toString()
            var price=price_up.text.toString()
            var trans=transmissionType
            var image= downloadUrl;
            var type=vehicleType

            if(image==""){
                Toast.makeText(this,"image field is empty select image from galley",Toast.LENGTH_LONG).show()
            }else{
                var id=database.push().key

                database.child(id.toString()).setValue(Model(make,model,price,trans,km,year,image,type))

                finish()
//            startActivity(Intent(this,UploadVehicleActivity::class.java))

                Toast.makeText(this,"AD POSTED SUCCESSFULLY",Toast.LENGTH_SHORT).show()
                Toast.makeText(this,"Your Ad is live now!",Toast.LENGTH_SHORT).show()
            }



        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null)
        {
            var bmp=data?.extras?.get("data") as Bitmap
            image_up.setImageBitmap(bmp)
            imageText_up.setHint("");


        }
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            var resultUri = data?.data
                image_up.setImageURI(resultUri)
                imageText_up.setHint("");
            try {
                if (resultUri != null) {
                    storageRef.putFile(resultUri).addOnSuccessListener { taskSnapshot: UploadTask.TaskSnapshot? ->

//                            var urll =storageRef.downloadUrl
//                            val dwnTxt = findViewById<View>(R.id.dwnTxt) as TextView
// ==============================to get the download image url
                        storageRef.getDownloadUrl()
                            .addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                                downloadUrl = uri.toString()
//                                make_up.setText(downloadUrl.toString())

                            })
//                            make_up.setText(url.toString())
//                            model_up.setText(urll.toString())
                        Toast.makeText(this, "Picture Successfully Uploaded :)", Toast.LENGTH_LONG).show()
                    }
                }
            }catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }

        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

}
