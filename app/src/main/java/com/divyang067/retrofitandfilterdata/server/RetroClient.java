package com.divyang067.retrofitandfilterdata.server;

import com.divyang067.retrofitandfilterdata.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * RetroClient class for set retrofit web api configuration
 */
public class RetroClient {

    //make static class one time
    private static RetroClient retroClient;

    /**
     * make one static instance to access any time from any class
     *
     * @return retrofit client instance
     */
    public static RetroClient getInstance() {
        if (retroClient == null) {
            retroClient = new RetroClient();
        }
        return retroClient;
    }


    /**
     * Get Retrofit Instance
     *
     * @return get retrofit instance
     */
    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return get api service
     */
    public ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
