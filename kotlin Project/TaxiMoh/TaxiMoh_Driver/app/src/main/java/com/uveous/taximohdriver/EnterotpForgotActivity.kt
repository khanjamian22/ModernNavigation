package com.uveous.loopfoonpay

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chaos.view.PinView
import com.google.android.material.textfield.TextInputEditText
import com.uveous.taximohdriver.R

class EnterotpForgotActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    lateinit var logindata: Button
    lateinit var enterotp: EditText
    var otp:Int=0
    var driverid:Int=0
    lateinit var pin: PinView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterotpforgot)
        toolbar=findViewById(R.id.toolbar)
        logindata=findViewById(R.id.logindata)
        enterotp=findViewById(R.id.enterotp)
        pin=findViewById(R.id.pin_view)

        otp=intent.getIntExtra("otp",0)
        driverid=intent.getIntExtra("driverid",0)

        Log.v("otp",otp.toString())
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this,ForgotActivity::class.java))
        })

        logindata.setOnClickListener(View.OnClickListener {
            if(pin.text.toString().contentEquals(otp.toString())){
                startActivity(Intent(this,ConfirmForgotActivity::class.java)
                        .putExtra("driverid",driverid))
                logindata.isEnabled=false

            }else{
                Toast.makeText(this,"OTP not matched",Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,ForgotActivity::class.java))

    }
}
