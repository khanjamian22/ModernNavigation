package com.uveous.taximohdriver

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uveous.loopfoonpay.LoginActivity

class Splash : AppCompatActivity(){

    var id :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        startService(Intent(baseContext, MyService::class.java))

        //  getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationbar));
        Handler().postDelayed({
            finish()
            val pref1: SharedPreferences = getSharedPreferences("TASK_ID", Context.MODE_PRIVATE)
            id = pref1.getInt("userid", 0)
            val intent = Intent(this@Splash, LoginActivity::class.java)
            startActivity(intent)
   /*         if (id == 0) {
                val intent = Intent(this@Splash, LoginActivity::class.java)
                startActivity(intent)
            } else{
                val intent = Intent(this@Splash, TravelDashboard::class.java)
                startActivity(intent)
            }*/

        }, 5000)
    }
}