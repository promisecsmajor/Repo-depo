package com.codepath.repo_depo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.auth0.android.Auth0;

import com.codepath.repo_depo.fragments.PostFragment;
import com.codepath.repo_depo.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Auth0 auth0;
    private Button btnLogOut;
    private TextView tvUserName;
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    final Fragment fragment1 = new PostFragment();
    final Fragment fragment2 = new PostFragment();
    final Fragment fragment3 = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //queryPosts();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //fragment = fragment1;
                        fragment = fragment1;
                        break;
//                    case R.id.:
//                        fragment = fragment2;
//                        //fragment = fragment2;
//                        break;
                    case R.id.action_profile:
                    default:
                        fragment = fragment3;
                        //fragment = fragment3;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);


    }
}