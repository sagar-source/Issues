package com.example.githubissues.API;

import com.example.githubissues.Model.Comments;
import com.example.githubissues.Model.Issues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("issues")
    Call<List<Issues>> getIssues();

    @GET("issues/{issue_id}/comments")
    Call<List<Comments>> getComments(@Path("issue_id") int issueID);
}
