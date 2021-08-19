package com.uveous.taximohdriver

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.taximohdriver.Api.Model.paymenthistory
import com.uveous.taximohdriver.Api.Model.paymentresult
import com.uveous.taximohdriver.Api.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Mywallet :  AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var earning: TextView
    lateinit var tv_bottom_cancel: TextView
    lateinit var filter: ImageView
    lateinit var recyclerview: RecyclerView
    lateinit var show: LinearLayout
    lateinit var ll_bottom_dialog: LinearLayout
    lateinit var array: LinearLayout
    lateinit var rv_bottom_sheet: ListView
    private lateinit var sessionManager: SessionManager
    private lateinit var tripListAdapter: HistoryListAdapter
    var timeList = ArrayList<String>()
    var arrayAdapter: ArrayAdapter<String>? = null
    var date:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallet)

        toolbar = findViewById(R.id.toolbar)
        array = findViewById(R.id.array)
        earning = findViewById(R.id.earning)
        filter = findViewById(R.id.filter)
        tv_bottom_cancel = findViewById(R.id.tv_bottom_cancel)
        recyclerview = findViewById(R.id.recyclerview)
        show = findViewById(R.id.show)
        ll_bottom_dialog = findViewById(R.id.ll_bottom_dialog)
        rv_bottom_sheet = findViewById(R.id.rv_bottom_sheet)

        tv_bottom_cancel.setOnClickListener(View.OnClickListener {
            ll_bottom_dialog.visibility= GONE
        })
        filter.setOnClickListener(View.OnClickListener {
            ll_bottom_dialog.visibility= VISIBLE
        })
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        sessionManager = SessionManager(this)
        timeList.add(0,"Total Earning")
        timeList.add(1,"Today")
        timeList.add(2,"Last 7 days")
        timeList.add(3,"Last 30 days")
        Log.d("checking",timeList.toString())
        arrayAdapter = ArrayAdapter<String>(this, R.layout.inflate_bottom_listview, timeList)

        rv_bottom_sheet.setAdapter(arrayAdapter)
        rv_bottom_sheet.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            if(position==0){
                date=""
                getHistory(date)
                ll_bottom_dialog.visibility= GONE
            }else if(position==1){
                date="1"
                getHistory(date)
                ll_bottom_dialog.visibility= GONE
            }else if(position==2){
                date="7"
                getHistory(date)
                ll_bottom_dialog.visibility= GONE
            }else if(position==3){
                date="30"
                getHistory(date)
                ll_bottom_dialog.visibility= GONE
            }
        })

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.layoutManager = layoutManager
        recyclerview.itemAnimator = DefaultItemAnimator()
        getHistory(date)



    }

    private fun getHistory(date : String) {
        try {
            val progressDialog = ProgressDialog(this)
            // progressDialog.setTitle("Kotlin Progress Bar")
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            progressDialog.setCanceledOnTouchOutside(false)
            var mAPIService: ApiService? = null
            mAPIService = ApiClient.apiService
            sessionManager.fetchuserid()?.let {
                mAPIService!!.getearninghistory(
                        "Bearer " + sessionManager.fetchAuthToken(),
                        it,date
                ).enqueue(object : Callback<paymenthistory> {
                    override fun onResponse(call: Call<paymenthistory>, response: Response<paymenthistory>) {
                        Log.i("gdfhfjf",response.toString())
                        Log.i("", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful()) {
                            Log.v("vvv", response.body().toString()!!)
                            var lo: paymenthistory = response.body()!!
                            if (lo.status == 200) {
                                progressDialog.dismiss()
                                show.visibility = VISIBLE
                                earning.text = lo.currency + lo.total_trip_price
                                if(lo.result.size==0){
                                    array.visibility= GONE
                                }else{
                                    array.visibility= VISIBLE
                                    tripListAdapter = HistoryListAdapter(lo.result, lo.currency, this@Mywallet)
                                    recyclerview.adapter = tripListAdapter
                                }

                            } else {
                                show.visibility = GONE
                                progressDialog.dismiss()
                                //  Toast.makeText(this@ProfileDetail, "not submiited", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                    override fun onFailure(call: Call<paymenthistory>, t: Throwable) {
                        Toast.makeText(this@Mywallet, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }catch (e:Exception){

        }
    }
    internal class HistoryListAdapter(private var paymentList: List<paymentresult>, var currency:String, val context : Context) :
            RecyclerView.Adapter<HistoryListAdapter.MyViewHolder>() {

        internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var currency: TextView = view.findViewById(R.id.currency)
            var pmode: TextView = view.findViewById(R.id.pmode)
            var rideno: TextView = view.findViewById(R.id.rideno)
            var price: TextView = view.findViewById(R.id.price)

        }
        @NonNull
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.paymenthistory, parent, false)
            return MyViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val tripresult = paymentList[position]
            holder.currency.text=currency
            holder.pmode.text=tripresult.payment_method
            holder.price.text=currency+tripresult.ride_price
            holder.rideno.text="CRN No #"+tripresult.request_id

        }

        override fun getItemCount(): Int {
            return paymentList.size
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
