package com.uveous.taximohdriver

import android.app.ActivityManager
import android.app.ProgressDialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.backgroundcheck
import com.uveous.taximohdriver.Api.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyService : Service() {

    private lateinit var sessionManager: SessionManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY;
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        sessionManager = SessionManager(applicationContext)
        offlineapi()
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return  null
    }

    private fun offlineapi() {
        try {
            var mAPIService: ApiService? = null
            mAPIService = ApiClient.apiService
             sessionManager.fetchuserid()?.let {
                mAPIService!!.offline(
                    "Bearer "+sessionManager.fetchAuthToken(), it).enqueue(object :
                    Callback<backgroundcheck> {
                    override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                        Log.v("response" , response.body().toString()!!)
                        if (response.isSuccessful()) {
                            var lo: backgroundcheck = response.body()!!
                            if(lo.status==200){
                                sessionManager.saveAvailabity("Offline")
                            }
                        }
                    }

                    override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                    }
                })
            }
        }catch (e:java.lang.Exception){

        }

    }
}