package com.uveous.loopfoonpay

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.RideResponse
import com.uveous.loopfoonpay.Api.Model.backgroundcheck
import com.uveous.taximohdriver.Api.SessionManager
import com.uveous.taximohdriver.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingActivity : AppCompatActivity(){

    lateinit var simpleRatingBar: RatingBar
    lateinit var submitreview: TextView
    private lateinit var sessionManager: SessionManager
    lateinit var et_post: EditText
    lateinit var name1:TextView
    lateinit var phone1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ratinglayout)
        sessionManager = SessionManager(this)
        simpleRatingBar=findViewById(R.id.simpleRatingBar)
        submitreview=findViewById(R.id.submit1)
        name1=findViewById(R.id.name1)
        phone1=findViewById(R.id.phone1)


/*
        submitreview.setOnClickListener(View.OnClickListener {
            et_post=findViewById(R.id.et_post)

            try {
                val progressDialog = ProgressDialog(this@RatingActivity)
                // progressDialog.setTitle("Kotlin Progress Bar")
                progressDialog.setMessage("Please wait")
                progressDialog.show()
                progressDialog.setCanceledOnTouchOutside(false)
                var mAPIService: ApiService? = null
                mAPIService = ApiClient.apiService
                mAPIService!!.review("Bearer " + sessionManager.fetchAuthToken(), lo1.request_id!!, sessionManager.fetchuserid()!!, simpleRatingBar.getRating().toString(), et_post.text.toString()).enqueue(object :
                        Callback<backgroundcheck> {
                    override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                        Log.i("", "post submitted to API." + response.body()!!)
                        Log.v("post" , response.body().toString()!!)
                        if (response.isSuccessful()) {
                            var lo: backgroundcheck = response.body()!!
                            if (lo.status == 200) {
                                progressDialog.dismiss()

                                Toast.makeText(this@RatingActivity, "Your Rating is submitted", Toast.LENGTH_SHORT).show()

                            } else {
                                progressDialog.dismiss()

                                Toast.makeText(this@RatingActivity, "Your Rating is not submitted", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                    override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                        Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }catch (e:java.lang.Exception){

            }
        })
*/
    }
}
