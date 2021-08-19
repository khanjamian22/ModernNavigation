package com.uveous.taximohdriver.Api.Model

import com.google.gson.annotations.SerializedName
import com.uveous.loopfoonpay.Api.Model.result3

data class paymenthistory(

        @SerializedName("status")
        var status:Int,
        @SerializedName("currency")
        var currency:String,
       @SerializedName("total_trip_price")
        var total_trip_price:String,
        @SerializedName("result")
        var result :ArrayList<paymentresult>

)

class paymentresult {
    @SerializedName("request_log_id")
    var request_log_id:Int?=0
    @SerializedName("request_id")
    var request_id:Int?=0
    @SerializedName("payment_method")
    var payment_method:String?=""
    @SerializedName("ride_price")
    var ride_price:String?=""
    @SerializedName("request_status")
    var request_status:String?=""
}




