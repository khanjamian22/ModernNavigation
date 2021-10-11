package com.lynhill.kioskxyz.networking;

import com.lynhill.kioskxyz.model.RegisterModel;
import com.lynhill.kioskxyz.model.UrlResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {
    /*TODO Registration */
    @FormUrlEncoded
    @POST("api/create.php")
    Call<RegisterModel> registration(@Field("email") String email,

                                     @Field("name") String name);

    /*TODO for get Media*/
    @GET("media/read.php")
    Call<UrlResponseModel> getAllMedialUrl();

}
