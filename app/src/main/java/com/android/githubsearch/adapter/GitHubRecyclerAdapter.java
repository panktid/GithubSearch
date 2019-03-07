package com.android.githubsearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.githubsearch.R;
import com.android.githubsearch.models.GitHubRepoDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GitHubRecyclerAdapter extends RecyclerView.Adapter<GitHubRecyclerAdapter.MyViewHolder> {

    Context mContext;

    public void setGitHubRepoDetailsList(List<GitHubRepoDetailsModel> gitHubRepoDetailsModelList) {
        this.gitHubRepoDetailsModelList = gitHubRepoDetailsModelList;
        notifyDataSetChanged();
    }

    List<GitHubRepoDetailsModel> gitHubRepoDetailsModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewAvatar;
        TextView txtRepoName, txtRepoDesc, txtRating;

        public MyViewHolder(View view) {
            super(view);

            imgViewAvatar = view.findViewById(R.id.imgViewAvatar);
            txtRepoName = view.findViewById(R.id.txtRepoName);
            txtRepoDesc = view.findViewById(R.id.txtRepoDesc);
            txtRating = view.findViewById(R.id.txtRating);
        }
    }

    public GitHubRecyclerAdapter(Context context, List<GitHubRepoDetailsModel> gitHubRepoDetailsModelList) {
        this.mContext = context;
        this.gitHubRepoDetailsModelList = gitHubRepoDetailsModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        GitHubRepoDetailsModel gitHubRepoDetailsModel = gitHubRepoDetailsModelList.get(position);

        // Rendering the owner avatar through Picasso..,
        Picasso.with(mContext).load(gitHubRepoDetailsModel.getOwner().getAvatarUrl()).into(holder.imgViewAvatar);

        holder.txtRepoName.setText(gitHubRepoDetailsModel.getName());
        holder.txtRepoDesc.setText(gitHubRepoDetailsModel.getDescription());
        holder.txtRating.setText(gitHubRepoDetailsModel.getStars() + "\u2605");
    }

    @Override
    public int getItemCount() {
        return gitHubRepoDetailsModelList == null ? 0 : gitHubRepoDetailsModelList.size();
    }
}
