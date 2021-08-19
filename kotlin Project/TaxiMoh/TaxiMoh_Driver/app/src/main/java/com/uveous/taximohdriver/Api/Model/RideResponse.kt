package com.uveous.loopfoonpay.Api.Model

import com.google.gson.annotations.SerializedName

data class RideResponse(
    @SerializedName("status")
    var status:Int,
    @SerializedName("request_id")
    val request_id: Int? = null,

 @SerializedName("name")
val name: String? = null,
 @SerializedName("mobile")
val mobile: String? = null,
 @SerializedName("request_date")
val request_date: String? = null,
 @SerializedName("request_time")
val request_time: String,
 @SerializedName("origin_address")
val origin_address: String? = null,
 @SerializedName("origin_longitude")
val origin_longitude: String? = null,
    @SerializedName("origin_latitude")
val origin_latitude: String? = null,
  @SerializedName("destination_address")
val destination_address: String? = null,
    @SerializedName("destination_longitude")
val destination_longitude: String? = null,
    @SerializedName("destination_latitude")
val destination_latitude: String? = null,
    @SerializedName("currency")
val currency: String? = null,
    @SerializedName("price")
    val price: String? = null,
  @SerializedName("distance")
    val distance: String? = null,
 @SerializedName("no_of_passengers")
    val no_of_passengers: Int? = null


)