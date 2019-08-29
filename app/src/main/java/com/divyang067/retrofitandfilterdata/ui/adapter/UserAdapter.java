package com.divyang067.retrofitandfilterdata.ui.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.divyang067.retrofitandfilterdata.R;
import com.divyang067.retrofitandfilterdata.databinding.ListItemUserBinding;
import com.divyang067.retrofitandfilterdata.model.Contact;

import java.util.List;


/**
 * Created by divyang067 on 2019/08/29.
 *
 * @author Divyang Patel
 * <p>
 * User Adapter class for display user data by using recyclerview adapter
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    //list data
    private List<Contact> listData;

    /**
     * refresh and reload adapter data
     *
     * @param listData
     */
    public void doRefresh(List<Contact> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_user, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        Contact dataObject = getItem(position);

        holder.binding.tvUserName.setText(!TextUtils.isEmpty(dataObject.getName()) ? dataObject.getName() : "");
        holder.binding.tvUserEmail.setText(!TextUtils.isEmpty(dataObject.getEmail()) ? dataObject.getEmail() : "");
        holder.binding.tvUserAddress.setText(!TextUtils.isEmpty(dataObject.getAddress()) ? dataObject.getAddress() : "");
        holder.binding.tvUserGender.setText(!TextUtils.isEmpty(dataObject.getGender()) ? dataObject.getGender() : "");
        holder.binding.tvUserPhone.setText(dataObject.getPhone() != null && !TextUtils.isEmpty(dataObject.getPhone().getAllPhone()) ? dataObject.getPhone().getAllPhone() : "");

        holder.binding.pbUser.setVisibility(View.VISIBLE);
        Glide.with(holder.binding.ivUser.getContext())
                .load(dataObject.getProfilePic())
                .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.binding.pbUser.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.binding.pbUser.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.binding.ivUser);


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    /**
     * get single item data from list by position
     *
     * @param position
     * @return
     */
    private Contact getItem(int position) {
        return listData.get(position);
    }

    /**
     * user view holder
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public ListItemUserBinding binding;

        public UserViewHolder(@NonNull ListItemUserBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
