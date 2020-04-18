package com.example.githubissues.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubissues.Model.Comments;
import com.example.githubissues.R;


import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    Context mCtx;
    List<Comments> commentList;

    public CommentsAdapter(Context mCtx, List<Comments> commentList) {
        this.mCtx = mCtx;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentsAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.comment_layout, parent, false);
        return new CommentsAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.CommentViewHolder holder, final int position) {
        holder.textView_comment.setText(commentList.get(position).getBody());
        holder.textView_author.setText(commentList.get(position).getAuthorName());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView textView_comment;
        TextView textView_author;

        public CommentViewHolder(View itemView) {
            super(itemView);

            textView_comment = itemView.findViewById(R.id.txt_comment);
            textView_author = itemView.findViewById(R.id.txt_author);
        }
    }
}


