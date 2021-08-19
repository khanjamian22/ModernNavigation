package com.uveous.taximohdriver

import android.app.DatePickerDialog
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

class Driverwallet :  AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var earning: TextView
    lateinit var dateshow: TextView
    lateinit var date: ImageView
    lateinit var recyclerview: RecyclerView
    lateinit var show: LinearLayout
    private lateinit var sessionManager: SessionManager
    private var mYear = 0
    private  var mMonth:Int = 0
    private  var mDay:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mywallet)

        toolbar = findViewById(R.id.toolbar)
        earning = findViewById(R.id.earning)
        dateshow = findViewById(R.id.dateshow)
        date = findViewById(R.id.date)
        recyclerview = findViewById(R.id.recyclerview)
        show = findViewById(R.id.show)


        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        sessionManager = SessionManager(this)


        date.setOnClickListener(View.OnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth -> dateshow.setText(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth) }, mYear, mMonth, mDay)
            datePickerDialog.show()
        })
    }
}


