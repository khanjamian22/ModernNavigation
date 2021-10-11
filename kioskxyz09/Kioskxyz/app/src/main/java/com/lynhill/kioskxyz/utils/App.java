package com.lynhill.kioskxyz.utils;

import android.app.Application;
import android.os.SystemClock;

import com.lynhill.kioskxyz.networking.ApiConstant;
import java.util.concurrent.TimeUnit;

import io.paperdb.Paper;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

   private static App instance;
    public static OkHttpClient okHttpClient = null;

    public App() {

    }

    public static App getInstance() {
        return instance;
    }
    /*TODO get Retrofit instance*/
    public static Retrofit getRetroInstance(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);

        Interceptor interceptor = chain -> {
            SystemClock.sleep(700);
            return chain.proceed(chain.request());
        };

        okHttpClient = null;



            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS)
                    .writeTimeout(100,TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addNetworkInterceptor(interceptor)
                    .dispatcher(dispatcher)
                    .build();

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initApplication();

        /*TODO init Paper library*/
        Paper.init(this);



    }
    /*TODO init Application class*/
    private void initApplication() {
        instance = this;
    }

}
