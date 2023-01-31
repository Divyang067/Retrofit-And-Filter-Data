package com.divyang067.retrofitandfilterdata.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * phone model class
 */
public class Phone {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("office")
    @Expose
    private String office;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    ///////////////////// Extra //////////////////////
    private String allPhone;

    public String getAllPhone() {
        if (TextUtils.isEmpty(allPhone)) {
            String allPhoneString = "";
            if (!TextUtils.isEmpty(getMobile())) {
                if (TextUtils.isEmpty(allPhone)) {
                    allPhoneString = getMobile();
                } else {
                    allPhoneString = allPhoneString + ", " + getMobile();
                }
            }
            if (!TextUtils.isEmpty(getHome())) {
                if (TextUtils.isEmpty(allPhoneString)) {
                    allPhoneString = getHome();
                } else {
                    allPhoneString = allPhoneString + ", " + getHome();
                }
            }
            if (!TextUtils.isEmpty(getOffice())) {
                if (TextUtils.isEmpty(allPhoneString)) {
                    allPhoneString = getOffice();
                } else {
                    allPhoneString = allPhoneString + ", " + getOffice();
                }
            }
            allPhone = allPhoneString;
        }
        return allPhone;
    }
    ///////////////////// Extra //////////////////////

}
