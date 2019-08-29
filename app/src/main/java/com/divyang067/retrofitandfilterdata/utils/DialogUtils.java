package com.divyang067.retrofitandfilterdata.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

import com.divyang067.retrofitandfilterdata.R;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * Dialog Utils Class for all dialog show from this class
 */
public final class DialogUtils {

    //make static class one time
    private static DialogUtils dialogFactory;

    /**
     * make one static instance to access any time from any class
     *
     * @return
     */
    public static DialogUtils getInstance() {
        if (dialogFactory == null) {
            dialogFactory = new DialogUtils();
        }
        return dialogFactory;
    }

    /**
     * create simple ok error dialog using string
     *
     * @param context
     * @param title
     * @param message
     * @return
     */
    public Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    /**
     * create simple ok error dialog using resource string
     *
     * @param context
     * @param titleResource
     * @param messageResource
     * @return
     */
    public Dialog createSimpleOkErrorDialog(Context context,
                                            @StringRes int titleResource,
                                            @StringRes int messageResource) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }

    /**
     * create generic error dialog using string
     *
     * @param context
     * @param message
     * @return
     */
    public Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    /**
     * create generic error dialog using resource string
     *
     * @param context
     * @param messageResource
     * @return
     */
    public Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    /**
     * create progress dialog using string
     *
     * @param context
     * @param message
     * @return
     */
    public ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /**
     * create progress dialog using resource string
     *
     * @param context
     * @param messageResource
     * @return
     */
    public ProgressDialog createProgressDialog(Context context,
                                               @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }

}
