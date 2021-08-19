package com.uveous.loopfoonpay

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.taximohdriver.Api.Model.profiledetail
import com.uveous.taximohdriver.Api.SessionManager
import com.uveous.taximohdriver.R
import com.uveous.taximohdriver.TravelDashboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDetail : AppCompatActivity(){

   lateinit var name:TextInputEditText
   lateinit var lname:TextInputEditText
   lateinit var email:TextInputEditText
   lateinit var dob:TextInputEditText
   lateinit var type:TextInputEditText
   lateinit var profile:ImageView
   lateinit var address:TextInputEditText
   lateinit var contact:TextInputEditText
    private lateinit var sessionManager: SessionManager
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        sessionManager = SessionManager(this)

        name=findViewById(R.id.name)
        lname=findViewById(R.id.lname)
        toolbar=findViewById(R.id.toolbar)
        email=findViewById(R.id.email)
        dob=findViewById(R.id.dob)
        type=findViewById(R.id.type)
        profile=findViewById(R.id.profile)
        address=findViewById(R.id.address)
        contact=findViewById(R.id.contact)

        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        try{
       val progressDialog = ProgressDialog(this)
    // progressDialog.setTitle("Kotlin Progress Bar")
    progressDialog.setMessage("Please wait")
    progressDialog.show()
    progressDialog.setCanceledOnTouchOutside(false)
    var mAPIService: ApiService? = null
    mAPIService = ApiClient.apiService
    sessionManager.fetchuserid()?.let {
        mAPIService!!.getprofile(
                "Bearer "+ sessionManager.fetchAuthToken(),
                it
        ).enqueue(object : Callback<profiledetail> {
            override fun onResponse(call: Call<profiledetail>, response: Response<profiledetail>) {
                Log.i("", "post submitted to API." + response.body()!!)
                if (response.isSuccessful()) {
                    Log.v("vvv", response.body().toString()!!)
                    var lo: profiledetail = response.body()!!
                    if (lo.status == 200) {
                        progressDialog.dismiss()
                        email.setText(lo.email)
                        name.setText(lo.first_name)
                        lname.setText(lo.last_name)
                        dob.setText(lo.dob)
                        contact.setText(lo.mobile)
                        if(lo.vehicle_type.equals("1")){
                            type.setText("Car")
                        }else{
                            type.setText("Bike")
                        }
                        address.setText(lo.address+","+lo.pincode)
                        Glide.with(this@ProfileDetail).load(lo.profile_photo).into(profile)

                    } else {
                        progressDialog.dismiss()
                        //  Toast.makeText(this@ProfileDetail, "not submiited", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<profiledetail>, t: Throwable) {
                Toast.makeText(this@ProfileDetail, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
        }catch (e:java.lang.Exception){

        }
}

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
