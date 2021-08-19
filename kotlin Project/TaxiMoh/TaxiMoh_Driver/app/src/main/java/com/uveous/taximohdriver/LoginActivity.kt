package com.uveous.loopfoonpay

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.countrylist
import com.uveous.loopfoonpay.Api.Model.result
import com.uveous.loopfoonpay.Api.Model.userlogin
import com.uveous.taximohdriver.Api.SessionManager
import com.uveous.taximohdriver.DriverAcccountSetupSteps
import com.uveous.taximohdriver.R
import com.uveous.taximohdriver.TravelDashboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var pwd:EditText
    private lateinit var sessionManager: SessionManager
    var listcountryall = ArrayList<result>()
    var listcountry = ArrayList<String>()
    var listcountryid = ArrayList<Int>()
    lateinit var code:LinearLayout
    lateinit var flag:ImageView
    lateinit var countrycode:TextView
    lateinit var dialog : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email=findViewById(R.id.email)
        pwd=findViewById(R.id.pwd)
        code=findViewById(R.id.code)
        flag=findViewById(R.id.flag)
        //flag=ImageView(this@LoginActivity)
        countrycode=findViewById(R.id.countrycode)
        dialog = Dialog(this@LoginActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        sessionManager = SessionManager(this)
        val butlogin= findViewById<Button>(R.id.login)
        val reg= findViewById<TextView>(R.id.reg)
        val forgotpassword= findViewById<TextView>(R.id.forgotpassword)
        getCountryfun()

        butlogin.setOnClickListener(View.OnClickListener {
            loginfun()
        })

        reg.setOnClickListener(View.OnClickListener {
            val i= Intent(this,SignUp::class.java)
            startActivity(i)
        })
        forgotpassword.setOnClickListener(View.OnClickListener {
            val i= Intent(this,ForgotActivity::class.java)
            startActivity(i)
        })

        code.setOnClickListener(View.OnClickListener {
            showDialog()
        })
    }


    private fun getCountryfun() {
        try{
            var mAPIService: ApiService? = null
            mAPIService = ApiClient.apiService
            mAPIService!!.country().enqueue(object : Callback<countrylist> {
                override fun onResponse(call: Call<countrylist>, response: Response<countrylist>) {
                    Log.i("", "post submitted to API." + response.body()!!)
                    if (response.isSuccessful()) {
                        var lo: countrylist = response.body()!!
                        if(lo.status==200){
                            var lo : countrylist =response.body()!!
                            listcountryall=lo.result
                            countrycode.text=lo.result.get(0).phone_code
                            Glide.with(this@LoginActivity).load(lo.result.get(0).icon+"32.png").into(flag)
                            for (i in 0 until listcountryall.size) {
                                listcountryall.get(i).country_name?.let { listcountry.add(it) }
                            }
                            for (i in 0 until listcountryall.size) {
                                listcountryall.get(i).id?.let { listcountryid.add(it) }
                            }

                        }else{

                        }

                    }
                }

                override fun onFailure(call: Call<countrylist>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e:java.lang.Exception){

        }
    }
    private lateinit var countrylistadapter: CountryListAdapter

    private fun showDialog() {
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.countrycode_layout)
        val window: Window = dialog.getWindow()!!
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        val recyclerview = dialog.findViewById(R.id.recyclerview) as RecyclerView
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.layoutManager = layoutManager
        recyclerview.itemAnimator = DefaultItemAnimator()

        countrylistadapter = CountryListAdapter(listcountryall,this@LoginActivity)
        recyclerview.adapter = countrylistadapter

        dialog.show()

    }


    fun loginfun(){

        if(email.text.toString().isEmpty()){
            Toast.makeText(this@LoginActivity,"Please enter email", Toast.LENGTH_SHORT).show()
        }else if(pwd.text.toString().isEmpty()){
            Toast.makeText(this@LoginActivity,"Please enter password", Toast.LENGTH_SHORT).show()
        }else {
            sessionManager.logoutUser()
         /*   val progressDialog = ProgressDialog(this@LoginActivity)
            // progressDialog.setTitle("Kotlin Progress Bar")
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            var mAPIService: ApiService? = null
            mAPIService = ApiClient.apiService
            sessionManager.fetchuserid()?.let {
                mAPIService!!.logout(
                        "Bearer "+sessionManager.fetchAuthToken(), it).enqueue(object : Callback<backgroundcheck> {
                    override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                        Log.i("", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful()) {
                            var lo: backgroundcheck = response.body()!!
                            if(lo.status==200){
                                val mSharedPreferences = applicationContext.getSharedPreferences("TASK_ID", 0)
                                mSharedPreferences?.edit()?.remove("userid")?.commit()
                                sessionManager.logoutUser()
                                startActivity(Intent(this@LoginActivity, LoginActivity::class.java)) //.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finishAffinity()
                                progressDialog.dismiss()
                                //   Toast.makeText(this@TravelDashboard,"Offline", Toast.LENGTH_SHORT).show()
                            }else{
                                progressDialog.dismiss()
                                //   Toast.makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                            }

                            *//*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                                startActivity(Intent)
        *//*
                        }
                    }

                    override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }*/
            try {
                val progressDialog = ProgressDialog(this@LoginActivity)
                // progressDialog.setTitle("Kotlin Progress Bar")
                progressDialog.setMessage("Please wait")
                progressDialog.show()
                var mAPIService: ApiService? = null
                mAPIService = ApiClient.apiService
                mAPIService!!.login(email.text.toString(), pwd.text.toString()).enqueue(object :
                        Callback<userlogin> {
                    override fun onResponse(call: Call<userlogin>, response: Response<userlogin>) {
                        Log.i("", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful()) {
                            var lo: userlogin = response.body()!!
                            lo.user_status?.let { Log.v("user_status", it) }
                            if (lo.status == 200) {
                                progressDialog.dismiss()
                                Toast.makeText(this@LoginActivity, "Login", Toast.LENGTH_SHORT).show()
                                if (lo.user_status.equals("verified")) {
                                    lo.api_token?.let { sessionManager.saveAuthToken(it) }
                                    lo.user_id?.let { sessionManager.saveuserid(it) }
                                    lo.user_name?.let { sessionManager.savename(it) }
                                    lo.user_mobile?.let { sessionManager.savenumber(it) }
                                    var i = Intent(this@LoginActivity,
                                            TravelDashboard::class.java)
                                    i.putExtra("token", lo.api_token)
                                    i.putExtra("userid", lo.user_id)
                                    startActivity(i)
                                } else if (lo.user_status.equals("new")) {
                                    var i = Intent(this@LoginActivity, DriverAcccountSetupSteps::class.java)
                                    i.putExtra("token", lo.api_token)
                                    i.putExtra("userid", lo.user_id)
                                    startActivity(i)
                                } else if (lo.user_status.equals("rejected")) {
                                    Toast.makeText(this@LoginActivity, lo.user_status, Toast.LENGTH_SHORT).show()
                                }

                                Log.v("dd", "post registration to API" + response.body()!!.toString())
                            } else {
                                progressDialog.dismiss()
                                Toast.makeText(this@LoginActivity, lo.msg, Toast.LENGTH_SHORT).show()
                            }
                            /*   val Intent = Intent(applicationContext, TravelDashboard::class.java)
                        startActivity(Intent)*/
                        }
                    }

                    override fun onFailure(call: Call<userlogin>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }catch (e:Exception){

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    inner class CountryListAdapter(private var counttrycode: List<result>,val context :Context) :
            RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var countryname: TextView = view.findViewById(R.id.countryname)
            var countrycode: TextView = view.findViewById(R.id.countrycode)
            var flag: ImageView = view.findViewById(R.id.flag)
            var code: LinearLayout = view.findViewById(R.id.code)

        }
        @NonNull
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclr_countrycode, parent, false)
            return MyViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val counttrycode = counttrycode[position]
            holder.countrycode.text=counttrycode.phone_code
            holder.countryname.text=counttrycode.country_name+" "+"("+counttrycode.country_code+")"
            Glide.with(context).load(counttrycode.icon+"32.png").into(holder.flag)

            holder.code.setOnClickListener(View.OnClickListener {
                 dialog.dismiss()
                 countrycode.text=counttrycode.phone_code
                 Glide.with(this@LoginActivity).load(counttrycode.icon+"32.png").into(flag)
            })
        }

        override fun getItemCount(): Int {
            return counttrycode.size
        }
    }


}