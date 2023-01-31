package com.divyang067.retrofitandfilterdata;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * application class
 */
public class App extends MultiDexApplication {

    //context
    private static App appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    /**
     * get application context
     *
     * @return application context
     */
    public static Context getAppContext() {
        return appInstance.getApplicationContext();
    }
}
