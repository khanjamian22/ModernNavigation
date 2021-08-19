package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class RequestAccept(
    @SerializedName("status")
    var status:Int,
    @SerializedName("valid_otp")
    var valid_otp:Int,
    @SerializedName("user_id")
    var user_id:Int,
      @SerializedName("name")
    var name:String,
   @SerializedName("mobile")
    var mobile:String



)