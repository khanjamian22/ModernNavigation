package com.uveous.taximohdriver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.uveous.loopfoonpay.SignUp

class Choose_vehicle_yes_no : AppCompatActivity(){
    private lateinit var card1:CardView
    private lateinit var card2:CardView
    private lateinit var txt1:TextView
    private lateinit var txt11:TextView
    private lateinit var txt2:TextView
    private lateinit var txt22:TextView
    private lateinit var next:TextView
    var apitoken:String?=""
    var userid:Int?=0
    lateinit var signUp :SignUp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choosevehicle)

        card1=findViewById(R.id.card1)
        card2=findViewById(R.id.card2)
        txt1=findViewById(R.id.txt1)
        txt11=findViewById(R.id.txt11)
        txt2=findViewById(R.id.txt2)
        txt22=findViewById(R.id.txt22)
        next=findViewById(R.id.next)


        apitoken=intent.getStringExtra("token")
        userid=intent.getIntExtra("userid",0)

        Log.v("token",apitoken.toString())
        Log.v("token",userid.toString())

        card1.setOnClickListener(View.OnClickListener {
            card1.setCardBackgroundColor(resources.getColor(R.color.black))
            txt1.setTextColor(resources.getColor(R.color.white))
            txt11.setTextColor(resources.getColor(R.color.white))
            card2.setCardBackgroundColor(resources.getColor(R.color.white))
            txt2.setTextColor(resources.getColor(R.color.black))
            txt22.setTextColor(resources.getColor(R.color.black))

        })

        card2.setOnClickListener(View.OnClickListener {
            card2.setCardBackgroundColor(resources.getColor(R.color.black))
            txt2.setTextColor(resources.getColor(R.color.white))
            txt22.setTextColor(resources.getColor(R.color.white))
            card1.setCardBackgroundColor(resources.getColor(R.color.white))
            txt1.setTextColor(resources.getColor(R.color.black))
            txt11.setTextColor(resources.getColor(R.color.black))

        })

        next.setOnClickListener(View.OnClickListener {
            val a= Intent(this,DriverAcccountSetupSteps::class.java)
             a.putExtra("token",apitoken)
             a.putExtra("userid", userid!!)
             startActivity(a)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
       // finish()
    }
}