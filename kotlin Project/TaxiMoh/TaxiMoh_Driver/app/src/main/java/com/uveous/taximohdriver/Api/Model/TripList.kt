package com.uveous.taximohdriver.Api.Model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class TripList(
        @SerializedName("status")
        var status:Int,
        @SerializedName("currency")
        var currency: String,
        @SerializedName("result")
        var result:ArrayList<tripresult>
)

class tripresult {
    @SerializedName("request_id")
    var request_id:Int ?=0
    @SerializedName("request_log_id")
    var request_log_id:Int ?=0
    @SerializedName("request_date")
    var request_date:String ?=""
    @SerializedName("request_time")
    var request_time:String ?=""
   @SerializedName("origin_address")
    var origin_address:String ?=""
   @SerializedName("destination_address")
    var destination_address:String ?=""
   @SerializedName("status")
    var status:String ?=""
    @SerializedName("total_trip_price")
    var total_trip_price:String ?=""
    @SerializedName("confirmation")
    var confirmation:String ?=""
}
