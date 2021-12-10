package com.codepath.repo_depo.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.repo_depo.R;
import com.codepath.repo_depo.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    Context context;
    List<User> users;

    public UsersAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("UsersAdapter", "onCreateViewHolder");
        View userView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        Log.d("UsersAdapter", "onBindViewHolder" + position);
        // Get the movie at the passed in postion
        User user = users.get(position);
        //Bind the movie data into the VH
        holder.bind(user);
    }
    // Clean all elements of the recycler
    public void clear() {
        users.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<User> list) {
        users.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvAccount;
        TextView tvDesc;
        ImageView ivProfile;
        ImageView ivIcon;
        ImageView ivGif;
        TextView tvLikeCount;
        Drawable drawable;
        Resources res;
        Drawable drawable2;
        Resources res2;

        public ViewHolder(@NonNull View itemview){
            super(itemview);
            tvAccount = itemview.findViewById(R.id.text_view_account_handle);
            tvDesc = itemview.findViewById(R.id.text_view_video_description);
            ivProfile = itemview.findViewById(R.id.ivProfileImage);
            ivIcon = itemview.findViewById(R.id.album_view);
            ivGif = itemview.findViewById(R.id.ivGif);
            tvLikeCount = itemview.findViewById(R.id.likeCount);
            res = context.getResources();
            drawable = ResourcesCompat.getDrawable(res, R.drawable.coders_u_icon, null);
            res2 = context.getResources();
            drawable2 = ResourcesCompat.getDrawable(res, R.drawable.github_logo, null);

        }

        public void bind(User user) {
            tvAccount.setText(user.getLogin());
            tvDesc.setText(user.getGit());
            tvLikeCount.setText(String.valueOf(user.getRepo_count()));
            ivGif.setImageResource(R.drawable.github_logo);
            Glide.with(context).load(user.getAvatar()).into(ivProfile);
            Glide.with(context).load(drawable).into(ivIcon);
            Glide.with(context).load("https://gist.githubusercontent.com/theAdityaNVS/f5b585d1082da2dffffea32434f37956/raw/7f9552d0a179b4f84059259fa878199e369b069c/GitHub-logo.gif").into(ivGif);
        }
    }
}
