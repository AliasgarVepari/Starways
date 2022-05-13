package com.aliasgar.vepari.starways;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(log).build();


        Retrofit rf = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://aliisadmin.000webhostapp.com/")
                .client(client)
                .build();
        return rf;
    }

    public static Login getUserService(){
        Login login = getRetrofit().create(Login.class);
        return login;
    }

    public static SignUp getSignup(){
        SignUp signup = getRetrofit().create(SignUp.class);
        return signup;
    }

}
