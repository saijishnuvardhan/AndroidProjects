package com.example.praneeth.shoping_cart;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit  retrofitCall(){

        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;

    }
}
