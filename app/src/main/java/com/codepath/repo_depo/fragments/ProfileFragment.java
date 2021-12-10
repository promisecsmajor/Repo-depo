package com.codepath.repo_depo.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.UserProfile;
import com.bumptech.glide.Glide;
import com.codepath.repo_depo.LoginActivity;
import com.codepath.repo_depo.R;

public class ProfileFragment extends Fragment {
    private Auth0 auth0;
    private TextView tvUserName;
    private TextView tvName;
    private ImageView profileImage;
    private ImageButton btnLogOut;
    public String imageURL;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvName = view.findViewById(R.id.tvName);
        profileImage = view.findViewById(R.id.ivProfileImage);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        auth0 = new Auth0(getContext());
        String accessToken = getActivity().getIntent().getStringExtra(LoginActivity.EXTRA_ACCESS_TOKEN);
        AuthenticationAPIClient authentication = new AuthenticationAPIClient(auth0);
        authentication
                .userInfo(accessToken)
                .start(new Callback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(UserProfile userProfile) {
                        imageURL = userProfile.getPictureURL();
                        tvName.setText("@" + userProfile.getNickname());
                        tvUserName.setText(userProfile.getName());
                        Glide.with(getContext()).load(imageURL).into(profileImage);
                    }

                    @Override
                    public void onFailure(@NonNull AuthenticationException e) {

                    }
                });

    }
    private void logout() {
        WebAuthProvider.logout(auth0)
                .withScheme("demo")
                .start(getContext(), new Callback<Void, AuthenticationException>() {
                    @Override
                    public void onSuccess(@Nullable Void payload) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.putExtra(LoginActivity.EXTRA_CLEAR_CREDENTIALS, true);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(@NonNull AuthenticationException error) {
                        //Log out canceled, keep the user logged in
                    }
                });
    }

}
