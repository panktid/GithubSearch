package com.android.githubsearch.models;

import com.google.gson.annotations.SerializedName;

public class GitHubRepoDetailsModel {

    private String name;

    private String description;

    @SerializedName("stargazers_count")
    private int stars;

    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
