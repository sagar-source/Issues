package com.example.githubissues.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.githubissues.Adapters.IssuesAdapter;
import com.example.githubissues.Model.Issues;
import com.example.githubissues.R;
import com.example.githubissues.ViewModel.ViewModel;

import java.util.List;

public class IssuesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    IssuesAdapter adapter;

    ProgressDialog dialog;

    ViewModel model;

    LayoutAnimationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);

        getSupportActionBar().setTitle("Issues");

        dialog = new ProgressDialog(IssuesActivity.this);
        dialog.setMessage("please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_down_to_up);

        swipeRefreshLayout = findViewById(R.id.swipe_issue);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        model = ViewModelProviders.of(this).get(ViewModel.class);

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

        model.getIssues().observe(this, new Observer<List<Issues>>() {
            @Override
            public void onChanged(@Nullable List<Issues> issueList) {
                adapter = new IssuesAdapter(IssuesActivity.this, issueList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutAnimation(controller);
                recyclerView.scheduleLayoutAnimation();
                dialog.dismiss();
            }
        });
    }
}
