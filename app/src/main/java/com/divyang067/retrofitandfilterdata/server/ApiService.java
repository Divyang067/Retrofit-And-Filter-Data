package com.divyang067.retrofitandfilterdata.server;

import com.divyang067.retrofitandfilterdata.model.GetUserListRs;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * interface class for retrofit web api calling to get responce from method
 */
public interface ApiService {

    /**
     * @return get list of contacts
     */
    @GET("/contact/get_all_contact")
    Call<GetUserListRs> getContacts();
}
