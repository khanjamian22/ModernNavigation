package com.example.mypractice.networking;

import com.example.mypractice.model.RoleUpdateResponse;
import com.example.mypractice.model.otp_model.OTPResponse;
import com.example.mypractice.model.otp_model.SendOTPResponse;
import com.example.mypractice.model.signup_model.SignUpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {

    /*TODO --------------------------------------------  Common  Apis   -------------------------------------- */


    /*TODO Registration */
    @FormUrlEncoded
    @POST("register")
    Call<SignUpModel> registration(@Field("first_name") String firstname,
                                   @Field("last_name")String lastname,
                                   @Field("email") String email,
                                   @Field("password") String password,
                                   @Field("otp")String otp);

    /*TODO opt verification*/
    @GET("otp-verify")
    Call<OTPResponse> otpVerification(@Query("otp") String otp,
                                     @Query("email") String email);

    /*TODO resend OTP */
    @FormUrlEncoded
    @POST("get-otp")
    Call<SendOTPResponse> getOTP(@Field("first_name")String fname,
                                 @Field("last_name")String lname,@Field("email")String email);

    /*TODO login */
    @FormUrlEncoded
    @POST("login")
    Call<SignUpModel> login(@Field("email") String email,
                            @Field("password") String password);


    /*TODO update Post*/
    @FormUrlEncoded
    @POST("update-role")
    Call<RoleUpdateResponse> roleUpdate(@Header("Authorization") String authToken, @Field("role") String role);


//
//
//    /*TODO update-android-id*/
//    @FormUrlEncoded
//    @POST("update-android-id")
//    Call<UpdateAndroidIdResponse> update_Android_Detail(@Header("Authorization") String authToken,
//                                                        @Field("ip_address")String ip_address,
//                                                        @Field("android_id")String androidID,
//                                                        @Field("device_name")String device_name);
//

//
//
//    /*TODO get Category*/
//    @GET("get-categories")
//    Call<GetCategoryResponse> getAllCategory();
//
//
//
//    /*TODO --------------------------------------------  User Apis   -------------------------------------- */
//
//
//    /*TODO Update Information*/
//    @FormUrlEncoded
//    @POST("user-details")
//    Call<UpdateUserInfoResponse> putUserInfo(@Header("Authorization") String authToken,
//                                             @Field("first_name")String firstname,
//                                             @Field("last_name")String lastname,
//                                             @Field("gender")String gender,
//                                             @Field("mobile")String mobile,
//                                             @Field("dob")String dob,
//                                             @Field("state")String state,
//                                             @Field("city")String city,
//                                             @Field("zipcode")String zipcode,
//                                             @Field("address")String address,
//                                             @Field("alt_mobile")String alt_mobile);
//
//
//    /*TODO post Work needed(android_id,category_id , title , description , job_date , job_time , amount , state , city , sub_city)*/
//    @FormUrlEncoded
//    @POST("job-post")
//    Call<PostWorkNeedResponse> postworkNedded(@Header("Authorization") String authToken,
//                                              @Field("android_id")String android_id,
//                                              @Field("category_id")String categoryId,
//                                              @Field("title")String title,
//                                              @Field("description")String des,
//                                              @Field("job_date")String job_date,
//                                              @Field("job_time")String job_time,
//                                              @Field("amount")String amount,
//                                              @Field("state")String state,
//                                              @Field("city")String city,
//                                              @Field("sub_city")String sub_city);
//
//
//
//    /*TODO Get Work needed(android_id,category_id , title , description , job_date , job_time , amount , state , city , sub_city)*/
//
//    @GET("get-user-job-posts")
//    Call<GetWorkPostResponse> getworkPost(@Header("Authorization") String authToken,
//                                          @Query("android_id") String android_id);
//
//
//
//
//
//    /*TODO --------------------------------------------  Service Provider Apis   -------------------------------------- */
//
//
//
//    /*TODO service-provider-details*/
//    @FormUrlEncoded
//    @POST("helper-details")
//    Call<UpdateUserInfoResponse> putServiceProviderInfo(@Header("Authorization") String authToken,
//                                                        @Field("first_name")String firstname,
//                                                        @Field("last_name")String lastname,
//                                                        @Field("gender")String gender,
//                                                        @Field("mobile")String mobile,
//                                                        @Field("avatar")String profile_image,
//                                                        @Field("state")String state,
//                                                        @Field("city")String city,
//                                                        @Field("description")String des,
//                                                        @Field("category")String category,
//                                                        @Field("area")String area
//    );
//
//
//    /*TODO Get Work post by category*/
//    @GET("get-job-posts-by-category")
//    Call<GetWorkPostAsCategoryResponse> getWorkPostByCategory(@Header("Authorization") String authToken,
//                                                              @Query("android_id") String android_id,
//                                                              @Query("category_id") String category_id);
//
//



}
