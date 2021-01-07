package com.buslayout.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInterface {
  private static final String BASE_URL =  "https://www.smsipl.com/Gasco/";
  public static final String KEY =  "38:59:60:99:E1:DC:01:14:1B:21:9E:8C:1F:D2:75:2F:A9:1C:70:AC";
  public static Retrofit retrofit = null;

  public static Retrofit getClient() {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.MINUTES)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .client(okHttpClient)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return retrofit;
  }

}
