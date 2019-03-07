package com.android.githubsearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetGitHubReposResponseModel {

    private String message;

    @SerializedName("items")
    private ArrayList<GitHubRepoDetailsModel> gitHubRepoDetailsArray;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<GitHubRepoDetailsModel> getGitHubRepoResponse() {
        return gitHubRepoDetailsArray;
    }

    public void setGitHubRepoResponse(ArrayList<GitHubRepoDetailsModel> listResponse) {
        this.gitHubRepoDetailsArray = listResponse;
    }
}
