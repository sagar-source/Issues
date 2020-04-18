package com.example.githubissues.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubissues.Adapters.CommentsAdapter;
import com.example.githubissues.Adapters.IssuesAdapter;
import com.example.githubissues.Model.Comments;
import com.example.githubissues.Model.Issues;
import com.example.githubissues.R;
import com.example.githubissues.ViewModel.ViewModel;

import java.util.List;

public class CommentsActivity extends AppCompatActivity implements ViewModel.CommentCallBack {

    int num;
    RecyclerView recyclerView;
    CommentsAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    ProgressDialog dialog;
    ViewModel model;

    LayoutAnimationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        getSupportActionBar().setTitle("Comments");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            num = bundle.getInt("number");
        }

        model = ViewModelProviders.of(this).get(ViewModel.class);

        model.setCommentCallBack(this);


        dialog = new ProgressDialog(CommentsActivity.this);
        dialog.setMessage("please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_down_to_up);

        swipeRefreshLayout = findViewById(R.id.swipe_comment);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                loadData();
            }
        });

    }

    private void loadData() {

        model.getComments(num).observe(this, new Observer<List<Comments>>() {
            @Override
            public void onChanged(@Nullable List<Comments> commentList) {
                adapter = new CommentsAdapter(CommentsActivity.this, commentList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutAnimation(controller);
                recyclerView.scheduleLayoutAnimation();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void showPopup() {
         dialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(CommentsActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_layout, null);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();


        dialogView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });

        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

}
