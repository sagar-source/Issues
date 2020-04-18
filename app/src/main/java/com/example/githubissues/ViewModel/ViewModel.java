package com.example.githubissues.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.githubissues.API.API;
import com.example.githubissues.Model.Comments;
import com.example.githubissues.Model.Issues;
import com.example.githubissues.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ViewModel extends androidx.lifecycle.ViewModel {

    private CommentCallBack commentCallBack;

    private MutableLiveData<List<Issues>> issueList;
    private MutableLiveData<List<Comments>> commentList;


    public LiveData<List<Issues>> getIssues() {

        if (issueList == null) {
            issueList = new MutableLiveData<List<Issues>>();

            loadIssues();
        }

        return issueList;
    }

    public LiveData<List<Comments>> getComments(int number) {

        if (commentList == null) {
            commentList = new MutableLiveData<List<Comments>>();

            loadComments(number);
        }

        return commentList;
    }


    private void loadIssues() {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        API api = retrofit.create(API.class);
        Call<List<Issues>> call = api.getIssues();


        call.enqueue(new Callback<List<Issues>>() {
            @Override
            public void onResponse(Call<List<Issues>> call, Response<List<Issues>> response) {

                issueList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Issues>> call, Throwable t) {

            }
        });
    }

    private void loadComments(int number) {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        API api = retrofit.create(API.class);
        Call<List<Comments>> call = api.getComments(number);


        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if (response.body() != null) {
                    if (response.body().isEmpty()) {
                        commentCallBack.showPopup();
                    }
                    else {
                        commentList.setValue(response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });
    }

    public void setCommentCallBack(CommentCallBack commentCallBack) {
        this.commentCallBack = commentCallBack;
    }

    public interface CommentCallBack {
        void showPopup();
    }

}
