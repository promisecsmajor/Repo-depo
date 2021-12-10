package com.codepath.repo_depo.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class User {
    String login;
    String avatar;
    String repos;
    String location;
    String git;
    int repo_count;

    public User(JSONObject jsonObject) throws JSONException{
        login = jsonObject.getString("login");
        avatar = jsonObject.getString("avatar_url");
        repos = jsonObject.getString("repos_url");
        location = jsonObject.getString("location");
        git = jsonObject.getString("html_url");
        repo_count = jsonObject.getInt("public_repos");
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRepos() {
        return repos;
    }

    public String getLocation() {
        return location;
    }

    public String getGit() {
        return git;
    }

    public int getRepo_count() {
        return repo_count;
    }
}