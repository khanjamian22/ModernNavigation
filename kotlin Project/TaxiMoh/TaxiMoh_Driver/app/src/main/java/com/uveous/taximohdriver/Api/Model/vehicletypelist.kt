package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class vehicletypelist(

    @SerializedName("status")
    var status:Int,

    @SerializedName("result")
    var result :ArrayList<result2>


)

  class result2 {
      @SerializedName("id")
      var id:Int ?=0
      @SerializedName("category_name")
      var category_name:String ?=""
    @SerializedName("create_at")
      var create_at:String ?=""

}
