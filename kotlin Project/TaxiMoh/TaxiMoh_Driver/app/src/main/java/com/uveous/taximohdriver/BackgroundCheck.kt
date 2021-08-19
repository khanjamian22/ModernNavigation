package com.uveous.taximohdriver

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.backgroundcheck
import com.uveous.loopfoonpay.Api.Model.userlogin
import com.uveous.loopfoonpay.LoginActivity
import com.uveous.taximohdriver.Api.SessionManager
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class BackgroundCheck : AppCompatActivity(){

    private lateinit var smartnumber:EditText
    private lateinit var submit:TextView
    var apitoken:String?=""
    var userid:Int?=0
    lateinit   var filePart1 : MultipartBody.Part
    lateinit   var filePart2 : MultipartBody.Part
    private lateinit var sessionManager: SessionManager
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.backgroundcheck)
        toolbar=findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this, DriverAcccountSetupSteps::class.java))
        })
        smartnumber=findViewById(R.id.smartnumber)
        submit=findViewById(R.id.submit)

        apitoken=intent.getStringExtra("token")
        userid=intent.getIntExtra("userid",0)
        Log.v("token",apitoken.toString())
        Log.v("token",userid.toString())
        sessionManager = SessionManager(this)

        submit.setOnClickListener(View.OnClickListener {
            submitfun()
        })
    }

    fun submitfun(){

        if(smartnumber.text.toString().isEmpty()){
            Toast.makeText(this@BackgroundCheck,"Please enter number", Toast.LENGTH_SHORT).show()
        }else {
            try {
                val progressDialog = ProgressDialog(this@BackgroundCheck)
                // progressDialog.setTitle("Kotlin Progress Bar")
                progressDialog.setMessage("Please wait")
                progressDialog.show()
                Log.v("tok", apitoken.toString());
                Log.v("tok", userid.toString());
                filePart1 = MultipartBody.Part.createFormData("user_id", userid.toString())
                filePart2 = MultipartBody.Part.createFormData("background_check", smartnumber.text.toString())
                var mAPIService: ApiService? = null
                mAPIService = ApiClient.apiService
                userid?.let {
                    mAPIService!!.backgroundcheck("Bearer " + apitoken, it, smartnumber.text.toString()).enqueue(object :
                            Callback<backgroundcheck> {
                        override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                            Log.v("", "post submitted to API." + response.body()!!)
                            Log.v("tok", response.toString());
                            if (response.isSuccessful()) {
                                var lo: backgroundcheck = response.body()!!
                                if (lo.status == 200) {
                                    val i = Intent(this@BackgroundCheck, DriverAcccountSetupSteps::class.java)
                                    i.putExtra("token", apitoken)
                                    i.putExtra("userid", userid!!)
                                    startActivity(i)
                                    progressDialog.dismiss()
                                    Toast.makeText(this@BackgroundCheck, "Submit", Toast.LENGTH_SHORT).show()

                                } else {
                                    progressDialog.dismiss()

                                    Toast.makeText(this@BackgroundCheck, "error", Toast.LENGTH_SHORT).show()
                                }

                            }
                        }


                        override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                            Log.v("tok", t.message.toString());
                            Toast.makeText(this@BackgroundCheck, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
                }

            }catch (e:Exception){

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i= Intent(this,DriverAcccountSetupSteps::class.java)
        i.putExtra("token",apitoken)
        i.putExtra("userid", userid!!)
        startActivity(i)
    }
}