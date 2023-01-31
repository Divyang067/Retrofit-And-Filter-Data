package com.divyang067.retrofitandfilterdata.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.divyang067.retrofitandfilterdata.App;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * Utils class for added all common function on this class
 */
public class Utils {

    /**
     * check internet connected or not
     *
     * @return internet connected or not in boolean (true or false)
     */
    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL("http://www.google.com/");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "test");
                urlConnection.setRequestProperty("Connection", "close");
                urlConnection.setConnectTimeout(1000); // mTimeout is in seconds
                urlConnection.connect();
                return urlConnection.getResponseCode() == 200;
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                return false;
            }
        }

        return false;

    }

    /**
     * hide keyboard
     *
     * @param context context
     * @param view view
     */
    public static void hideKeyboardFrom(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

}
