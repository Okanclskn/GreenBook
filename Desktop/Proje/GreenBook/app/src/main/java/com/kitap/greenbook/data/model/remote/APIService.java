package com.kitap.greenbook.data.model.remote;

import java.lang.annotation.Repeatable;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface APIService {

    @FormUrlEncoded
    @POST("/newuser")
    Call<RegisterResponse> registerUser(
            @Field("name") String name,
            @Field("surname") String surname,
            @Field("email") String email,
            @Field("password") String password

    );




    @POST("/login")
    Call<Void> loginUSer(@Body LoginResponse loginResponse);

}
