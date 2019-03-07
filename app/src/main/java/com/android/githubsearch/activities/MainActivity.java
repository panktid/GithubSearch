package com.android.githubsearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.githubsearch.R;
import com.android.githubsearch.adapter.GitHubRecyclerAdapter;
import com.android.githubsearch.apis.ApiGetHandler;
import com.android.githubsearch.interfaces.ApiResponseListener;
import com.android.githubsearch.models.GetGitHubReposResponseModel;
import com.android.githubsearch.models.GitHubRepoDetailsModel;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GitHubRecyclerAdapter gitHubRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        final EditText edtTxtInput = findViewById(R.id.edtTxtInput);
        Button btnSearch = findViewById(R.id.btnSearch);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = edtTxtInput.getText().toString();
                if(TextUtils.isEmpty(searchQuery)){
                    Toast.makeText(MainActivity.this, "Please provide search query", Toast.LENGTH_LONG).show();
                    return;
                }
                makeGitHubServiceCall(searchQuery);
            }
        });

        gitHubRecyclerAdapter = new GitHubRecyclerAdapter(this, null);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gitHubRecyclerAdapter);
    }

    private void makeGitHubServiceCall(String searchQuery) {

        //Making api call through Volley.
        String url = "https://api.github.com/search/repositories?q=" + searchQuery + "&sort=stars&order=desc";
        ApiGetHandler.makeGetApiRequest(this, url, "GITHUB_API", null, new ApiResponseListener() {
            @Override
            public void onSuccessResponse(String apiTag, String resJsonStr) {
                parseResponse(resJsonStr);
            }

            @Override
            public void onErrorResponse(String apiTag, VolleyError error) {

            }
        });
    }

    private void parseResponse(String resJsonStr) {
        //Parsing the json response through Gson.
        Gson gson = new Gson();
        GetGitHubReposResponseModel responseModel = gson.fromJson(resJsonStr, GetGitHubReposResponseModel.class);
        gitHubRecyclerAdapter.setGitHubRepoDetailsList(responseModel.getGitHubRepoResponse());
    }

    /*private List<GitHubRepoDetailsModel> sortByStarCount(List<GitHubRepoDetailsModel> gitHubRepoDetailsModelList){
        Collections.sort(gitHubRepoDetailsModelList,
                new Comparator<GitHubRepoDetailsModel>() {
                    @Override
                    public int compare(GitHubRepoDetailsModel o1, GitHubRepoDetailsModel o2) {
                        return o2.getStars()-o1.getStars();
                    }
                });
        return gitHubRepoDetailsModelList;
    }*/
}
