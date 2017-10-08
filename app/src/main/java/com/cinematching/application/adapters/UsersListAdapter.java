package com.cinematching.application.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinematching.application.R;
import com.cinematching.application.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jean-Christophe Melikian on 08/10/2017.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private Context mContext;
    private List<User> users = new ArrayList<>();

    public UsersListAdapter(Context context, @Nullable List<User> users) {
        this.mContext = context;
        if (users != null) {
            this.users = users;
        } else {
            this.users = new ArrayList<>();
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void addUser(User user) {
        this.users.add(user);
        notifyItemInserted(users.size() - 1);
    }

    public void addUsers(Collection<User> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View userCellView = inflater.inflate(R.layout.cell_list_users, parent, false);

        return new ViewHolder(userCellView);
    }

    @Override
    public void onBindViewHolder(UsersListAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.nickname.setText(user.getNickname());
        holder.city.setText(user.getCity());
        holder.city.setText(user.getZipcode());
        holder.age.setText(String.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        return (users != null) ? users.size() : 0;
    }

    public Context getContext() {
        return mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePicture;
        TextView nickname;
        TextView city;
        TextView age;
        TextView zipcode;

        public ViewHolder(View itemView) {
            super(itemView);
            profilePicture = (ImageView) itemView.findViewById(R.id.usersList_image);
            nickname = (TextView) itemView.findViewById(R.id.usersList_nickname);
            city = (TextView) itemView.findViewById(R.id.usersList_city);
            age = (TextView) itemView.findViewById(R.id.usersList_age);
            zipcode = (TextView) itemView.findViewById(R.id.usersList_zipcode);
        }
    }
}
