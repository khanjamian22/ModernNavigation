package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class citylist(

    @SerializedName("status")
    var status:Int,

    @SerializedName("result")
    var result :ArrayList<result3>


)

  class result3 {
      @SerializedName("id")
      var id:Int ?=0
      @SerializedName("city_name")
      var city_name:String ?=""

}
