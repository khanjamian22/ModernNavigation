package com.example.mypractice.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.SystemClock;

import com.example.mypractice.R;
import com.example.mypractice.networking.ApiConstance;
import com.example.mypractice.networking.TLSSocketFactory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.paperdb.Paper;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static final String CHANNEL_ID = "default_channel_id";
    private static App instance;
    public static OkHttpClient okHttpClient = null;

    public App() {

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

        TLSSocketFactory tlsSocketFactory = null;
        okHttpClient = null;

        try{
            tlsSocketFactory = new TLSSocketFactory();
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS)
                    .writeTimeout(100,TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addNetworkInterceptor(interceptor)
                    .dispatcher(dispatcher)
                    .sslSocketFactory(tlsSocketFactory,tlsSocketFactory.getTrustManager())
                    .build();
        }catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        return new Retrofit.Builder()
                .baseUrl(ApiConstance.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initApplication();
        set_Calligraphy();

        /*TODO init Paper library*/
        Paper.init(this);



    }


    public static App getInstance() {
        return instance;
    }

    /**
     * TODO set font calligraphy
     */
    private void set_Calligraphy() {


        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()

                                .setDefaultFontPath("fonts/Regular.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        Paper.init(this);
    }


    /*TODO init Application class*/
    private void initApplication() {
        instance = this;
    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "Test Deck";
            String description = "Test Deck";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) notificationManager.createNotificationChannel(channel);


        }

    }
}
