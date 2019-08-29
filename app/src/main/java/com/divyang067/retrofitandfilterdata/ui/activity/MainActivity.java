package com.divyang067.retrofitandfilterdata.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.divyang067.retrofitandfilterdata.R;
import com.divyang067.retrofitandfilterdata.databinding.ActivityMainBinding;
import com.divyang067.retrofitandfilterdata.model.Contact;
import com.divyang067.retrofitandfilterdata.model.GetUserListRs;
import com.divyang067.retrofitandfilterdata.server.RetroClient;
import com.divyang067.retrofitandfilterdata.ui.adapter.UserAdapter;
import com.divyang067.retrofitandfilterdata.utils.Utils;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * Main Activity Class for main screen
 */
public class MainActivity extends BaseActivity {

    //binding
    private ActivityMainBinding binding;
    //adapter
    private UserAdapter userAdapter;
    //all user data
    private List<Contact> allUserData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initListener();
        setData();
    }

    /**
     * initialization listener
     */
    private void initListener() {

        binding.srlUserList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Utils.hideKeyboardFrom(getBaseContext(), MainActivity.this.getCurrentFocus());

                binding.edtSearch.setText("");
                binding.edtSearch.clearFocus();
                //call web api for reload data
                getUserListCall();
            }
        });

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //filter data by search string
                filterData(editable.toString());
            }
        });

    }

    /**
     * filter data from search string
     *
     * @param filterString
     */
    private void filterData(String filterString) {
        List<Contact> filteredList = Lists.newArrayList(Collections2.filter(allUserData,
                new ContactFilter(filterString)));
        setAdapterData(filteredList);
    }

    /**
     * predicate for filter contact data from list
     */
    public final class ContactFilter implements Predicate<Contact> {
        private final String filterString;

        public ContactFilter(final String filterString) {
            this.filterString = filterString;
        }

        @Override
        public boolean apply(final Contact input) {
            return (!TextUtils.isEmpty(input.getName()) && input.getName().toLowerCase().contains(filterString.toLowerCase()))
                    || (!TextUtils.isEmpty(input.getEmail()) && input.getEmail().toLowerCase().contains(filterString.toLowerCase()))
                    || (!TextUtils.isEmpty(input.getAddress()) && input.getAddress().toLowerCase().contains(filterString.toLowerCase()))
                    || (!TextUtils.isEmpty(input.getGender()) && input.getGender().toLowerCase().contains(filterString.toLowerCase()))
                    || (input.getPhone() != null && !TextUtils.isEmpty(input.getPhone().getAllPhone()) && input.getPhone().getAllPhone().toLowerCase().contains(filterString.toLowerCase()));
        }
    }

    /**
     * set data
     */
    private void setData() {
        //call web api
        getUserListCall();
    }

    /**
     * get user list web api call
     */
    private void getUserListCall() {

        //show progress
        showProgress();

        RetroClient.getInstance().getApiService().getContacts().enqueue(new Callback<GetUserListRs>() {
            @Override
            public void onResponse(Call<GetUserListRs> call, Response<GetUserListRs> response) {
                //hide progress
                hideProgress();

                if (binding.srlUserList.isRefreshing()) {
                    binding.srlUserList.setRefreshing(false);
                }

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getContacts() != null) {
                        allUserData = response.body().getContacts();
                    }
                    setAdapterData(allUserData);
                } else {
                    try {
                        showError(response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserListRs> call, Throwable t) {
                //hide progress
                hideProgress();

                if (binding.srlUserList.isRefreshing()) {
                    binding.srlUserList.setRefreshing(false);
                }

                showError(t.getMessage());
            }
        });

    }

    /**
     * set adapter data on recycler view
     *
     * @param list_contact
     */
    private void setAdapterData(List<Contact> list_contact) {
        if (userAdapter == null) {
            userAdapter = new UserAdapter();
        }
        userAdapter.doRefresh(list_contact);

        if (binding.rvUserList.getAdapter() == null) {
            binding.rvUserList.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseContext(), RecyclerView.VERTICAL);
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_divider));
            binding.rvUserList.addItemDecoration(dividerItemDecoration);
            binding.rvUserList.setAdapter(userAdapter);
        }

    }

}
