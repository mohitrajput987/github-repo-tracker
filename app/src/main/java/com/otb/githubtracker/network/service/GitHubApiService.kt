package com.otb.githubtracker.network.service

import com.otb.githubtracker.feature.pullrequest.PullRequestModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mohit Rajput on 05/08/22.
 */
interface GitHubApiService {
    @GET("repos/{org_name}/{repo_name}/pulls")
    suspend fun fetchPullRequests(
        @Path("org_name") organizationName: String,
        @Path("repo_name") repositoryName: String,
        @Query("state") state: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): Response<List<PullRequestModels.PullRequestResponse>>
}