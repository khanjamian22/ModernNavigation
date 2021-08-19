package com.uveous.loopfoonpay.Api

import com.uveous.loopfoonpay.Api.Model.*
import com.uveous.taximohdriver.Api.Model.*
import com.uveous.taximohdriver.BackgroundCheck
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @FormUrlEncoded
    @POST("driver/signup/create-account")
    fun register(@Field("first_name") name: String,@Field("last_name") lname: String,@Field("mobile") mobile:String,@Field("email") email:String,@Field("address") address:String,
                 @Field("city") city: String,  @Field("pincode") pincode: String, @Field("country") country: String,@Field("state") state: String, @Field("vehicle_is") vehicle_is: String,  @Field("vehicle_type") vehicle_type : String, @Field("dob") dob: String,@Field("password") password: String): Call<usersignup>


    @FormUrlEncoded
    @POST("login/driver-login")
    fun login(@Field("mobile") mobile: String,@Field("password") password: String) :Call<userlogin>

   @FormUrlEncoded
    @POST("driver/forgot-password/check-username-exist")
    fun forgotemail(@Field("username") mobile: String) :Call<backgroundcheck>


   @FormUrlEncoded
    @POST("driver/forgot-password/change-password")
    fun forgotconfirmpassword(@Field("user_id") user_id: Int,@Field("password") password: String) :Call<backgroundcheck>


    @GET("driver/trip/get-trip-details")
    fun getTripDetail(@Header("Authorization") token :String,@Query("request_log_id") request_log_id:Int,@Query("user_id") user_id:Int):Call<TripDetailModel>

    @GET("driver/trip/get-earning-history")
    fun getearninghistory(@Header("Authorization") token :String,@Query("user_id") user_id:Int,@Query("from_date") from_date:String):Call<paymenthistory>

    @FormUrlEncoded
    @POST("driver/uploaded/background-check")
    fun backgroundcheck(@Header("Authorization") token :String, @Field("user_id") userid:Int,  @Field("background_check") smartno:String) :Call<backgroundcheck>

    @FormUrlEncoded
    @POST("driver/trip/create-review")
    fun review(@Header("Authorization") token :String,@Field("request_id") request_id: Int,@Field("driver_id") driver_id: Int,@Field("rating") rating: String,@Field("comment") comment: String) :Call<backgroundcheck>


    @FormUrlEncoded
    @POST("driver/driver-online")
    fun online(@Header("Authorization") token :String, @Field("user_id") userid:Int,  @Field("driver_latitude") driver_latitude:String,  @Field("driver_longitude") driver_longitude:String) :Call<backgroundcheck>

    @FormUrlEncoded
    @POST("driver/driver-offline")
    fun offline(@Header("Authorization") token :String, @Field("user_id") userid:Int) :Call<backgroundcheck>

    @FormUrlEncoded
    @POST("driver/request/confirmation")
    fun accept(@Header("Authorization") token :String, @Field("user_id") userid:Int, @Field("request_id") request_id:Int, @Field("confirmation") confirmation:String, @Field("msg") msg:String) :Call<RequestAccept>

    @FormUrlEncoded
    @POST("driver/request/completed")
    fun ridecomplete(@Header("Authorization") token :String, @Field("user_id") userid:Int, @Field("request_id") request_id:Int) :Call<backgroundcheck>

    @FormUrlEncoded
    @POST("driver/request/driver-request")
    fun  getRide(@Header("Authorization") token :String, @Field("user_id") userid:Int) :Call<RideResponse>

   @FormUrlEncoded
    @POST("driver/driver-logout")
    fun logout(@Header("Authorization") token :String, @Field("user_id") userid:Int) :Call<backgroundcheck>

    @GET("get/countries")
    fun country():Call<countrylist>

    @GET("get/states")
    fun state( @Query("country_id") country_id:Int):Call<statelist>


    @GET("driver/trip/get-all-trips")
    fun getTripList(@Header("Authorization") token :String,  @Query("user_id") user_id:Int):Call<TripList>

   @GET("get/cities")
    fun city( @Query("state_id") country_id:Int):Call<citylist>

    @GET("get/categories")
    fun getvehicletype():Call<vehicletypelist>

    @GET("driver/details/where-clauses")
    fun getprofile(@Header("Authorization") token :String,@Query("user_id") user_id:Int):Call<profiledetail>
    @GET("driver/trip/get-today-earning-history")
    fun gettodayearning(@Header("Authorization") token :String,@Query("user_id") user_id:Int):Call<todayearning>

    @Multipart
    @POST("driver/uploaded/profile-photo")
    fun profilephoto(@Header("Authorization") token:String,@Part userid: MultipartBody.Part?,@Part image: MultipartBody.Part?): Call<backgroundcheck>

    @Multipart
    @POST("driver/uploaded/driving-licence")
    fun drivinglicense(@Header("Authorization") token:String,@Part filePart1:MultipartBody.Part, @Part filePart:MultipartBody.Part) :Call<backgroundcheck>

    @Multipart
    @POST("driver/uploaded/vehicle-insurance")
    fun vehicleinsurance(@Header("Authorization") token:String,@Part filePart1:MultipartBody.Part, @Part filePart:MultipartBody.Part) :Call<backgroundcheck>

    @Multipart
    @POST("driver/uploaded/vehicle-registration")
    fun vehicleregistration(@Header("Authorization") token:String,@Part filePart1:MultipartBody.Part, @Part filePart:MultipartBody.Part) :Call<backgroundcheck>

    @GET("get/document-status")
    fun documentstatus(@Header("Authorization") token:String, @Query("user_id") userid:Int) :Call<documentstatus>

}