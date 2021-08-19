package com.uveous.taximohdriver

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.LoginActivity
import com.uveous.taximohdriver.Api.Model.documentstatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DriverAcccountSetupSteps  : AppCompatActivity(){
    private lateinit var card1:CardView
    private lateinit var card2:CardView
    private lateinit var card3:CardView
    private lateinit var card4:CardView
    private lateinit var card5:CardView
    private lateinit var img1:ImageView
    private lateinit var img2:ImageView
    private lateinit var img3:ImageView
    private lateinit var img4:ImageView
    private lateinit var img5:ImageView
    var apitoken:String?=""
    var userid:Int?=0
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accountsteps)

        if (checkPermission()) {
        } else {
            requestPermission();
        }
        toolbar=findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        })
        card1=findViewById(R.id.card1)
        card2=findViewById(R.id.card2)
        card3=findViewById(R.id.card3)
        card4=findViewById(R.id.card4)
        card5=findViewById(R.id.card5)
        img1=findViewById(R.id.img1)
        img2=findViewById(R.id.img2)
        img3=findViewById(R.id.img3)
        img4=findViewById(R.id.img4)
        img5=findViewById(R.id.img5)

        apitoken=intent.getStringExtra("token")
        userid=intent.getIntExtra("userid",0)

        Log.v("token",apitoken.toString())
        Log.v("token",userid.toString())

        try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        mAPIService!!.documentstatus("Bearer " + apitoken,userid!!).enqueue(object :
                Callback<documentstatus> {
            override fun onResponse(call: Call<documentstatus>, response: Response<documentstatus>) {
                Log.v("submitted" , response.body().toString()!!)
                Log.v("submitted1" ,userid.toString()!!)
                if (response.isSuccessful()) {
                    var lo: documentstatus = response.body()!!
                    if (lo.status == 200) {
                        var backgroundcheck=lo.driver_doc_status.getBackground_check();
                        var profilephoto=lo.driver_doc_status.getProfile_photo();
                        var drivinglicence=lo.driver_doc_status.getDriving_licence();
                        var vehicleinsurance=lo.driver_doc_status.getVehicle_insurance();
                        var vehicleregistration=lo.driver_doc_status.getVehicle_registration();
                        if(backgroundcheck!!.status.equals("verified")){
                            card1.isEnabled=false
                            card1.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_24))
                            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval1))
                        }else if(backgroundcheck!!.status.equals("pending")){
                            card1.isEnabled=false
                            card1.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_query_builder_24))
                            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval2))
                        }else if(backgroundcheck!!.status.equals("rejected")){
                            card1.isEnabled=false
                            card1.setCardBackgroundColor(resources.getColor(R.color.white))
                            img1.setImageResource(R.drawable.rejected)
                            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval3))

                        }else{
                            card1.isEnabled=true
                            card1.setCardBackgroundColor(resources.getColor(R.color.white))
                            img1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24))
                            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
                        }

                        if(profilephoto!!.status.equals("verified")){
                            card2.isEnabled=false
                            card2.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_24))
                            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval1))
                        }else if(profilephoto!!.status.equals("pending")){
                            card2.isEnabled=false
                            card2.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_query_builder_24))
                            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval2))
                        }else if(profilephoto!!.status.equals("rejected")){
                            card2.isEnabled=false
                            card2.setCardBackgroundColor(resources.getColor(R.color.white))
                            img2.setImageResource(R.drawable.rejected)
                            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval3))

                        }else{
                            card2.isEnabled=true
                            card2.setCardBackgroundColor(resources.getColor(R.color.white))
                            img2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24))
                            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
                        }


                        if(drivinglicence!!.status.equals("verified")){
                            card3.isEnabled=false
                            card3.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_24))
                            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval1))
                        }else if(drivinglicence!!.status.equals("pending")){
                            card3.isEnabled=false
                            card3.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_query_builder_24))
                            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval2))
                        }else if(drivinglicence!!.status.equals("rejected")){
                            card3.isEnabled=false
                            card3.setCardBackgroundColor(resources.getColor(R.color.white))
                            img3.setImageResource(R.drawable.rejected)
                            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval3))

                        }else{
                            card3.isEnabled=true
                            card3.setCardBackgroundColor(resources.getColor(R.color.white))
                            img3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24))
                            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
                        }


                        if(vehicleinsurance!!.status.equals("verified")){
                            card4.isEnabled=false
                            card4.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_24))
                            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval1))
                        }else if(vehicleinsurance!!.status.equals("pending")){
                            card4.isEnabled=false
                            card4.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_query_builder_24))
                            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval2))
                        }else if(vehicleinsurance!!.status.equals("rejected")){
                            card4.isEnabled=false
                            card4.setCardBackgroundColor(resources.getColor(R.color.white))
                            img4.setImageResource(R.drawable.rejected)
                            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval3))

                        }else{
                            card4.isEnabled=true
                            card4.setCardBackgroundColor(resources.getColor(R.color.white))
                            img4.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24))
                            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
                        }

                        if(vehicleregistration!!.status.equals("verified")){
                            card5.isEnabled=false
                            card5.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_24))
                            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval1))
                        }else if(vehicleregistration!!.status.equals("pending")){
                            card5.isEnabled=false
                            card5.setCardBackgroundColor(resources.getColor(R.color.blue))
                            img5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_query_builder_24))
                            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval2))
                        }else if(vehicleregistration!!.status.equals("rejected")){
                            card5.isEnabled=false
                            card5.setCardBackgroundColor(resources.getColor(R.color.white))
                            img5.setImageResource(R.drawable.rejected)
                            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval3))

                        }else{
                            card5.isEnabled=true
                            card5.setCardBackgroundColor(resources.getColor(R.color.white))
                            img5.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_right_24))
                            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
                        }


                        Log.v("dd", "post registration to API" + response.body()!!.toString())
                    } else {

                    }
                    /*   val Intent = Intent(applicationContext, TravelDashboard::class.java)
                       startActivity(Intent)*/
                }
            }

            override fun onFailure(call: Call<documentstatus>, t: Throwable) {
                Toast.makeText(this@DriverAcccountSetupSteps, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        }catch (e:java.lang.Exception){

        }

        card1.setOnClickListener(View.OnClickListener {
            card1.setCardBackgroundColor(resources.getColor(R.color.blue))
            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card2.setCardBackgroundColor(resources.getColor(R.color.white))
            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card3.setCardBackgroundColor(resources.getColor(R.color.white))
            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card4.setCardBackgroundColor(resources.getColor(R.color.white))
            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card5.setCardBackgroundColor(resources.getColor(R.color.white))
            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            val i= Intent(this,BackgroundCheck::class.java)
            i.putExtra("token",apitoken)
            i.putExtra("userid", userid!!)
            startActivity(i)

        })

        card2.setOnClickListener(View.OnClickListener {
            card2.setCardBackgroundColor(resources.getColor(R.color.blue))
            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card1.setCardBackgroundColor(resources.getColor(R.color.white))
            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card3.setCardBackgroundColor(resources.getColor(R.color.white))
            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card4.setCardBackgroundColor(resources.getColor(R.color.white))
            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card5.setCardBackgroundColor(resources.getColor(R.color.white))
            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            val i= Intent(this,ProfilePhoto::class.java)
            i.putExtra("token",apitoken)
            i.putExtra("userid", userid!!)
            startActivity(i)
        })

        card3.setOnClickListener(View.OnClickListener {
            card3.setCardBackgroundColor(resources.getColor(R.color.blue))
            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card2.setCardBackgroundColor(resources.getColor(R.color.white))
            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card1.setCardBackgroundColor(resources.getColor(R.color.white))
            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card4.setCardBackgroundColor(resources.getColor(R.color.white))
            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card5.setCardBackgroundColor(resources.getColor(R.color.white))
            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            val i= Intent(this,driverLicense::class.java)
            i.putExtra("token",apitoken)
            i.putExtra("userid", userid!!)
            startActivity(i)
        })

        card4.setOnClickListener(View.OnClickListener {
            card4.setCardBackgroundColor(resources.getColor(R.color.blue))
            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card2.setCardBackgroundColor(resources.getColor(R.color.white))
            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card3.setCardBackgroundColor(resources.getColor(R.color.white))
            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card1.setCardBackgroundColor(resources.getColor(R.color.white))
            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card5.setCardBackgroundColor(resources.getColor(R.color.white))
            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            val i= Intent(this,VehicleInsurance::class.java)
            i.putExtra("token",apitoken)
            i.putExtra("userid", userid!!)
            startActivity(i)
        })

        card5.setOnClickListener(View.OnClickListener {
            card5.setCardBackgroundColor(resources.getColor(R.color.blue))
            img5.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card2.setCardBackgroundColor(resources.getColor(R.color.white))
            img2.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card3.setCardBackgroundColor(resources.getColor(R.color.white))
            img3.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card1.setCardBackgroundColor(resources.getColor(R.color.white))
            img1.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            card4.setCardBackgroundColor(resources.getColor(R.color.white))
            img4.setBackgroundDrawable(resources.getDrawable(R.drawable.bgoval))
            val i= Intent(this,VehicleRegistration::class.java)
            i.putExtra("token",apitoken)
            i.putExtra("userid", userid!!)
            startActivity(i)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i= Intent(this,LoginActivity::class.java)
        startActivity(i)
    }
    private fun checkPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            false
        } else true
    }
    private val PERMISSION_REQUEST_CODE = 200

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        showMessageOKCancel("You need to allow access permissions",
                                DialogInterface.OnClickListener { dialog, which ->
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermission()
                                    }
                                })
                    }
                }            }
        }
    }


    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show()
    }


}

