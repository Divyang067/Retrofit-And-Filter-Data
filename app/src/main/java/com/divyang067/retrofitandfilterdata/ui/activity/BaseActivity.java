package com.divyang067.retrofitandfilterdata.ui.activity;

import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.divyang067.retrofitandfilterdata.R;
import com.divyang067.retrofitandfilterdata.utils.DialogUtils;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * Base Activity Class for extend any activity class to access show/hide progress and show error
 */
public class BaseActivity extends AppCompatActivity {

    //progressbar
    private ProgressDialog progressDialog;

    /**
     * show progressbar
     */
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = DialogUtils.getInstance().createProgressDialog(this, R.string.please_wait);
        }
        progressDialog.show();
    }

    /**
     * hide progressbar
     */
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * show error
     *
     * @param message
     */
    public void showError(String message) {
        DialogUtils.getInstance().createGenericErrorDialog(this, message).show();
    }

}
