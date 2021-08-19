package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class todayearning(
    @SerializedName("status")
    var status:Int,
      @SerializedName("currency")
    var currency:String,
    @SerializedName("today_earning")
    var today_earning:String


)