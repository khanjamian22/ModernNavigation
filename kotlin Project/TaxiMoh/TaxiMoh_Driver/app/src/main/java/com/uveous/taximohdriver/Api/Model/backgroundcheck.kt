package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class backgroundcheck(
    @SerializedName("status")
    var status:Int,
    @SerializedName("driver_id")
    var driver_id:Int,
    @SerializedName("forgot_otp")
    var forgot_otp:Int,
      @SerializedName("msg")
    var msg:String





)