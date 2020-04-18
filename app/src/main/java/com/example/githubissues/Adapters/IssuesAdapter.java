package com.example.githubissues.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubissues.Model.Issues;
import com.example.githubissues.R;
import com.example.githubissues.View.CommentsActivity;

import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssueViewHolder> {

    Context mCtx;
    List<Issues> issueList;

    public IssuesAdapter(Context mCtx, List<Issues> issueList) {
        this.mCtx = mCtx;
        this.issueList = issueList;
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.issue_layout, parent, false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder holder, final int position) {
        holder.textView.setText(issueList.get(position).getTitle());
        holder.textView_body.setText(issueList.get(position).getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, CommentsActivity.class);
                intent.putExtra("number",issueList.get(position).getNumber());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    class IssueViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView_body;

        public IssueViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text);
            textView_body = itemView.findViewById(R.id.text_body);
        }
    }
}

