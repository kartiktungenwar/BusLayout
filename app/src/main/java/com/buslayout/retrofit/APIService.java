package com.buslayout.retrofit;

import com.buslayout.home.model.HomeModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //The login call
    @FormUrlEncoded
    @POST("seat-map.php?key=3ec9864e9db5fb479a338d108503d4612fc65b80")
    Call<HomeModel> callBusLayoutApi(
            @Field("auth_key") String auth_key,
            @Field("password") String password);
}
