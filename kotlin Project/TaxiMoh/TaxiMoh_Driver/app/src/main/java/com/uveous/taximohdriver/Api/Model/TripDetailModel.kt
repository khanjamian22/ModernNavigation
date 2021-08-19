package com.uveous.taximohdriver.Api.Model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class TripDetailModel(
@SerializedName("status")
var status:Int,
 @SerializedName("currency")
var currency: String,
@SerializedName("request_id")
 var request_id:Int,
@SerializedName("request_log_id")
 var request_log_id:Int,
@SerializedName("request_date")
var request_date:String,
@SerializedName("request_time")
var request_time:String,
@SerializedName("origin_address")
var origin_address:String,
@SerializedName("destination_address")
var destination_address:String,
@SerializedName("total_trip_price")
var total_trip_price:String,
@SerializedName("request_status")
var request_status:String,
@SerializedName("name")
var name:String,
@SerializedName("mobile")
var mobile:String,
@SerializedName("origin_longitude")
var origin_longitude:String,
@SerializedName("origin_latitude")
var origin_latitude:String,
@SerializedName("destination_longitude")
var destination_longitude:String,
@SerializedName("destination_latitude")
var destination_latitude:String,
@SerializedName("confirmation")
var confirmation:String,
@SerializedName("distance")
var distance:String,
@SerializedName("base_fare_price")
var base_fare_price:String,
@SerializedName("km_price")
var km_price:String,
@SerializedName("taxes_rate")
var taxes_rate:String,
@SerializedName("tax")
var tax:String,
@SerializedName("trip_rating")
var trip_rating:String

)
