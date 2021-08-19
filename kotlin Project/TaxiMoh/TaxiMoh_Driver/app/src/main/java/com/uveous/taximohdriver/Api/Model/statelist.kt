package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class statelist(

    @SerializedName("status")
    var status:Int,

    @SerializedName("result")
    var result :ArrayList<result1>


)

  class result1 {
      @SerializedName("id")
      var id:Int ?=0
      @SerializedName("state_name")
      var state_name:String ?=""

}
