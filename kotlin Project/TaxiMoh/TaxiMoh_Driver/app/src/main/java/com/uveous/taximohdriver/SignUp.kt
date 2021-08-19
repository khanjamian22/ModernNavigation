package com.uveous.loopfoonpay

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.*
import com.uveous.taximohdriver.Choose_vehicle_yes_no
import com.uveous.taximohdriver.DriverAcccountSetupSteps
import com.uveous.taximohdriver.R
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SignUp : AppCompatActivity() {
    lateinit var dob: EditText
    lateinit var name: EditText
    lateinit var lname: EditText
    lateinit var email: EditText
    lateinit var phone: EditText
    lateinit var pwd: EditText
    lateinit var city: Spinner
    lateinit var postalcode: EditText
    lateinit var house: EditText
    lateinit var vehicletype:Spinner
    lateinit var countrylist:Spinner
    lateinit var statelist:Spinner
    lateinit var radioGroup: RadioGroup
    private var mYear = 0
    private  var mMonth:Int = 0
    private  var mDay:Int = 0
    private  var countryid:Int = 0
    private  var stateid:Int = 0
    private  var cityid:Int = 0
    private  var type:Int = 0
    lateinit var selectionvehicle :String
    lateinit var vehicleis :String
    var liststate = ArrayList<String>()
    var listcity = ArrayList<String>()
    var listcityid = ArrayList<Int>()
    var liststateid = ArrayList<Int>()
    var getVehicletype = ArrayList<String>()
    var listcountry = ArrayList<String>()
    var listcountryid = ArrayList<Int>()
    var listcountryall = ArrayList<result>()
    var liststateall = ArrayList<result1>()
    var listcityall = ArrayList<result3>()
    var getVehicletypeall = ArrayList<result2>()
    var token:String?=""
    lateinit var toolbar: Toolbar
    lateinit var code:LinearLayout
    lateinit var flag:ImageView
    lateinit var countrycode:TextView
    lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        toolbar=findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        })
        dob=findViewById(R.id.dob)
        name=findViewById(R.id.name)
        lname=findViewById(R.id.lname)
        email=findViewById(R.id.email)
        phone=findViewById(R.id.phone)
        pwd=findViewById(R.id.pwd)
        city=findViewById(R.id.city)
        house=findViewById(R.id.house)
        postalcode=findViewById(R.id.postalcode)
        vehicletype=findViewById(R.id.vehicletype)
        countrylist=findViewById(R.id.countrylist)
        statelist=findViewById(R.id.statelist)
        code=findViewById(R.id.code)
        flag=findViewById(R.id.flag)
        countrycode=findViewById(R.id.countrycode)
        dialog = Dialog(this@SignUp)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        liststate.add(0, "State")
        liststateid.add(0, 0)
        listcountry.add(0, "Country")
        listcountryid.add(0, 0)
        listcity.add(0, "City")
        listcityid.add(0, 0)


        getVehicletype.add(0, "What type of vehicle will you drive?")
        code.setOnClickListener(View.OnClickListener {
            showDialog()
        })

        statelist.setAdapter(ArrayAdapter(this, android.R.layout.simple_spinner_item, liststate))
        countrylist.setAdapter(ArrayAdapter(this, android.R.layout.simple_spinner_item, listcountry))
        vehicletype.setAdapter(ArrayAdapter(this, android.R.layout.simple_spinner_item, getVehicletype))
        city.setAdapter(ArrayAdapter(this, android.R.layout.simple_spinner_item, listcity))

        getCountryfun()
        getVehicletype()

        countrylist.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                if (i == 0) {

                } else {
                    try{
                        countryid = listcountryid.get(i)
                        Log.v("jsonObject", countryid.toString())
                        getStatefun()
                    }catch (e: Exception){
                        Log.v("jsonObject",e.message.toString())
                    }

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })


        statelist.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    i: Int,
                    l: Long
                ) {
                    if (i == 0) {
                    } else {
                        try{
                            stateid = liststateid.get(i)
                            Log.v("jsonObject", stateid.toString())
                            getcityfun()
                        }catch (e: Exception){
                            Log.v("jsonObject",e.message.toString())
                        }

                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            })


        city.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                i: Int,
                l: Long
            ) {
                if (i == 0) {
                } else {
                    try{
                        cityid = listcityid.get(i)
                        Log.v("jsonObject", stateid.toString())
                    }catch (e: Exception){
                        Log.v("jsonObject",e.message.toString())
                    }

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })


     vehicletype.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    i: Int,
                    l: Long
                ) {
                    if (i == 0) {
                    } else if(i==1) {
                        type=1
                    }else{
                        type=2
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            })


        radioGroup=findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                    val radio: RadioButton = findViewById(checkedId)
                      selectionvehicle=radio.text.toString()
            if(selectionvehicle.contentEquals("I have a vehicle")){
                vehicleis="YES"
            }else{
                vehicleis="No"
            }
                  /*  Toast.makeText(applicationContext," On checked change :"+
                            " ${radio.text}",
                            Toast.LENGTH_SHORT).show()*/
                })


        dob.setOnClickListener(View.OnClickListener {
        val c: Calendar = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth -> dob.setText(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth) }, mYear, mMonth, mDay)
        datePickerDialog.show()

        })

        val signup=findViewById<Button>(R.id.signup)
        val login=findViewById<TextView>(R.id.login)

        signup.setOnClickListener(View.OnClickListener {
            signupfun()
           /* val Intent= Intent(this,TravelDashboard::class.java)
            startActivity(Intent)*/
        })
        login.setOnClickListener(View.OnClickListener {
            val Intent= Intent(this,LoginActivity::class.java)
            startActivity(Intent)
        })
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

        countrylistadapter = CountryListAdapter(listcountryall,this@SignUp)
        recyclerview.adapter = countrylistadapter

        dialog.show()

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
                        Glide.with(this@SignUp).load(lo.result.get(0).icon+"32.png").into(flag)
                        for (i in 0 until listcountryall.size) {
                            listcountryall.get(i).country_name?.let { listcountry.add(it) }
                        }
                        for (i in 0 until listcountryall.size) {
                            listcountryall.get(i).id?.let { listcountryid.add(it) }
                        }
                        countrylist.adapter = ArrayAdapter(this@SignUp, R.layout.support_simple_spinner_dropdown_item,
                            listcountry
                        ) as SpinnerAdapter
                    }else{

                    }

                }
            }

            override fun onFailure(call: Call<countrylist>, t: Throwable) {
                Toast.makeText(this@SignUp, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        }catch (e:java.lang.Exception){

        }
    }

    private fun getVehicletype() {
        getVehicletype.clear()
        getVehicletype.add(0, "What type of vehicle will you drive?")
        try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        mAPIService!!.getvehicletype().enqueue(object : Callback<vehicletypelist> {
            override fun onResponse(call: Call<vehicletypelist>, response: Response<vehicletypelist>) {
                Log.i("", "post submitted to API." + response.body()!!)
                if (response.isSuccessful()) {
                    var lo: vehicletypelist = response.body()!!
                    if(lo.status==200){
                        var lo : vehicletypelist =response.body()!!
                        getVehicletypeall=lo.result

                        for (i in 0 until getVehicletypeall.size) {
                            getVehicletypeall.get(i).category_name?.let { getVehicletype.add(it) }
                        }

                        vehicletype.adapter = ArrayAdapter(
                            this@SignUp,
                            R.layout.support_simple_spinner_dropdown_item,
                            getVehicletype
                        ) as SpinnerAdapter
                    }else{

                    }

                }
            }

            override fun onFailure(call: Call<vehicletypelist>, t: Throwable) {
                Toast.makeText(this@SignUp, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        }catch (e:java.lang.Exception){

        }
    }

    private fun getstate() {
        try {
            Log.v("jsonObject", countryid.toString())
            val objectRequest = JsonObjectRequest(Request.Method.GET, "http://uveoustech.com/taximoh/api/get/states?country_id="+countryid, null,
                    com.android.volley.Response.Listener { response ->
                        try {
                            Log.v("jsonObject", response.toString())

                        } catch (e: Exception) {
                        }
                    }, com.android.volley.Response.ErrorListener { Toast.makeText(this, "Internal Server Error", Toast.LENGTH_LONG).show() })
            val requestQueue1 = Volley.newRequestQueue(this)
            requestQueue1.add(objectRequest)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    private fun getStatefun() {
        liststate.clear()
        liststate.add(0,"State")
try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        mAPIService!!.state(countryid).enqueue(object : Callback<statelist> {
            override fun onResponse(call: Call<statelist>, response: Response<statelist>) {
                Log.v("post submitted to API." , response.body().toString()!!)
                if (response.isSuccessful()) {
                    var lo: statelist = response.body()!!
                    if(lo.status==200){
                        liststateall=lo.result

                        for (i in 0 until liststateall.size) {
                            liststateall.get(i).state_name?.let { liststate.add(it) }
                        }
                            for (i in 0 until liststateall.size) {
                            liststateall.get(i).id?.let { liststateid.add(it) }
                        }

                        statelist.adapter = ArrayAdapter(
                            this@SignUp,
                            R.layout.support_simple_spinner_dropdown_item,
                            liststate
                        ) as SpinnerAdapter
                    }else{

                    }

                }
            }

            override fun onFailure(call: Call<statelist>, t: Throwable) {
                Toast.makeText(this@SignUp, t.message, Toast.LENGTH_SHORT).show()
            }
        })
}catch (e:java.lang.Exception){

}
    }

    private fun getcityfun() {
        listcity.clear()
        listcity.add(0,"City")
          try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        mAPIService!!.city(stateid).enqueue(object : Callback<citylist> {
            override fun onResponse(call: Call<citylist>, response: Response<citylist>) {
                Log.v("post submitted to API." , response.body().toString()!!)
                if (response.isSuccessful()) {
                    var lo: citylist = response.body()!!
                    if(lo.status==200){
                        listcityall=lo.result

                        for (i in 0 until listcityall.size) {
                            listcityall.get(i).city_name?.let { listcity.add(it) }
                        }

                        for (i in 0 until listcityall.size) {
                            listcityall.get(i).id?.let { listcityid.add(it) }
                        }

                        city.adapter = ArrayAdapter(
                            this@SignUp,
                            R.layout.support_simple_spinner_dropdown_item,
                            listcity
                        ) as SpinnerAdapter
                    }else{

                    }

                }
            }

            override fun onFailure(call: Call<citylist>, t: Throwable) {
                Toast.makeText(this@SignUp, t.message, Toast.LENGTH_SHORT).show()
            }
        })
          }catch (e:java.lang.Exception){

          }
    }

    fun signupfun(){
        if(email.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please enter email", Toast.LENGTH_SHORT).show()
        }else if(pwd.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please enter password", Toast.LENGTH_SHORT).show()
        }else if(phone.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please enter phone number", Toast.LENGTH_SHORT).show()
        }else if(name.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please enter first name", Toast.LENGTH_SHORT).show()
        }else if(lname.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please enter last name", Toast.LENGTH_SHORT).show()
        }else if(dob.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please select dob", Toast.LENGTH_SHORT).show()
        }else if(vehicleis.isEmpty()){
            Toast.makeText(this@SignUp,"Please select availabilty of vehicle", Toast.LENGTH_SHORT).show()
        }else if(house.text.toString().isEmpty()){
            Toast.makeText(this@SignUp,"Please enter address", Toast.LENGTH_SHORT).show()
        }else {
            try {
                val progressDialog = ProgressDialog(this@SignUp)
                // progressDialog.setTitle("Kotlin Progress Bar")
                progressDialog.setMessage("Please wait")
                progressDialog.show()
                var mAPIService: ApiService? = null
                mAPIService = ApiClient.apiService
                mAPIService!!.register(
                        name.text.toString(), lname.text.toString(),
                        phone.text.toString(),
                        email.text.toString(),
                        house.text.toString(),
                        cityid.toString(),
                        postalcode.text.toString(),
                        countryid.toString(),
                        stateid.toString(),
                        vehicleis,
                        type.toString(),
                        dob.text.toString(),
                        pwd.text.toString()).enqueue(object : Callback<usersignup> {
                    override fun onResponse(call: Call<usersignup>, response: Response<usersignup>) {
                        Log.i("", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful()) {
                            var lo: usersignup = response.body()!!
                            if (lo.status == 200) {
                                token = lo.api_token
                                progressDialog.dismiss()
                                val mPrefs: SharedPreferences = this@SignUp.getSharedPreferences(
                                        "TASK_ID",
                                        Context.MODE_PRIVATE
                                )
                                val prefsEditor = mPrefs.edit()
                                lo.user_id?.let { prefsEditor.putInt("userid", it) }
                                prefsEditor.commit()
                                Toast.makeText(this@SignUp, "Register", Toast.LENGTH_SHORT).show()
                                var i = Intent(this@SignUp, DriverAcccountSetupSteps::class.java)
                                i.putExtra("token", lo.api_token)
                                i.putExtra("userid", lo.user_id)
                                startActivity(i)
                                Log.v("dd", "post registration to API" + response.body()!!.toString())
                            } else {
                                progressDialog.dismiss()
                                Toast.makeText(this@SignUp, lo.msg, Toast.LENGTH_SHORT).show()
                            }

                            /*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                        startActivity(Intent)
*/
                        }
                    }

                    override fun onFailure(call: Call<usersignup>, t: Throwable) {
                        Toast.makeText(this@SignUp, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }catch (e:java.lang.Exception){

            }
        }
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
                Glide.with(this@SignUp).load(counttrycode.icon+"32.png").into(flag)
            })
        }

        override fun getItemCount(): Int {
            return counttrycode.size
        }
    }

}
