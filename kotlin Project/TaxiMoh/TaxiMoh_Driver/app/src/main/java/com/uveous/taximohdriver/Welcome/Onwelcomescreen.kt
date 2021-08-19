package com.uveous.loopfoonpay.Welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.uveous.loopfoonpay.LoginActivity
import com.uveous.taximohdriver.R
import com.uveous.taximohdriver.TravelDashboard


class Onwelcomescreen : AppCompatActivity(){
    private var i = 0
    private val hdlr: Handler = Handler()
    lateinit var progressbar:ProgressBar
    var id :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_homescreen)

        progressbar=findViewById(R.id.progressbar)
        Handler().postDelayed({
            val intent = Intent(this@Onwelcomescreen, welcomescreen::class.java)
            startActivity(intent)
         //   finish()
          /*  val pref1: SharedPreferences = getSharedPreferences("TASK_ID", Context.MODE_PRIVATE)
            id = pref1.getInt("userid", 0)
            if (id == 0) {
                val intent = Intent(this@Onwelcomescreen, welcomescreen::class.java)
                startActivity(intent)
            } else{
                val intent = Intent(this@Onwelcomescreen, LoginActivity::class.java)
                startActivity(intent)
            }*/

        }, 2000)
    }
}