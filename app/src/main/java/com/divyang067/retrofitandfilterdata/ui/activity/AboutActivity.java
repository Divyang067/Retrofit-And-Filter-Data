package com.divyang067.retrofitandfilterdata.ui.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.divyang067.retrofitandfilterdata.R;
import com.divyang067.retrofitandfilterdata.databinding.ActivityAboutBinding;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * About Activity Class for about screen
 */
public class AboutActivity extends BaseActivity {

    //binding
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);

        initListener();
        setData();
    }

    /**
     * initialization listener
     */
    private void initListener() {

    }

    /**
     * set data
     */
    private void setData() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
