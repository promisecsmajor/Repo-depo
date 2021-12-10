package com.codepath.repo_depo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.repo_depo.R;
import com.codepath.repo_depo.adapters.UsersAdapter;
import com.codepath.repo_depo.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class PostFragment extends Fragment {
    public static final String CONFIG_URL = "https://api.github.com/users";
    public static final String TAG = "PostFragment";
    private RecyclerView rvPosts;
    protected UsersAdapter adapter;
    SwipeRefreshLayout swipeContainer;

    List<User> allPosts;
    List<String> user_urls;
    List<User> users;
    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = new ArrayList<>();
        user_urls = new ArrayList<>();

        RequestHeaders requestHeaders = new RequestHeaders();
        requestHeaders.put("Authorization","Bearer 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");
        RequestParams params = new RequestParams();
        params.put("since", "XXX");
        AsyncHttpClient setup = new AsyncHttpClient();
        setup
                .get(CONFIG_URL, requestHeaders, params , new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.d(TAG, "onSuccess");
                        try {
                            JSONArray jsonArray = json.jsonArray;
                            for(int i = 0; i < jsonArray.length(); i++) {
                                user_urls.add(jsonArray.getJSONObject(i).getString("url"));
                                //Log.i(TAG, "Url: " + jsonArray.getJSONObject(i).getString("url"));
                                //Log.i(TAG, "Url: " + users.get(i));
                            }
                            RequestHeaders request = new RequestHeaders();
                            request.put("Authorization","Bearer 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");
                            RequestParams params = new RequestParams();
                            params.put("key", "value");
                            AsyncHttpClient client = new AsyncHttpClient();
                            for (String url : user_urls) {
                                client.get(url, request, params, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                                        Log.d(TAG, "onSuccessClient");
                                        try {
                                            JSONObject jsonObject = json.jsonObject;
                                            users.add(new User(jsonObject));
                                            //Log.i(TAG, users.get(0).getLogin());
                                        } catch (JSONException e) {
                                            Log.e(TAG, "Hit json exception", e);
                                            e.printStackTrace();
                                        }
                                    }
                                    @Override
                                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                                    }
                                });
                            }

                        } catch (JSONException e) {
                            Log.e(TAG, "Hit json exception", e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.d(TAG, "onFailureAsync");
                    }
                });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        swipeContainer = view.findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "fetching new data!");
                adapter.clear();
                adapter.addAll(users);
                swipeContainer.setRefreshing(false);
            }
        });

        allPosts = new ArrayList<>();
        adapter = new UsersAdapter(getContext(), allPosts);

        // Steps to use the recycler view
        // 0. create layout for one row in the list
        // 1. create the adapter
        // 2. create the data source
        // 3. set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        allPosts.addAll(users);
        adapter.addAll(users);
        adapter.notifyDataSetChanged();
    }
}
