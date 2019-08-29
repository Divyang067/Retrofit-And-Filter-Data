package com.divyang067.retrofitandfilterdata;

import android.app.Application;
import android.content.Context;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * application class
 */
public class App extends Application {

    //context
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    /**
     * get application context
     *
     * @return
     */
    public static Context getAppContext() {
        return context;
    }
}
