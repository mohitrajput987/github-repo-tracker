package com.otb.githubtracker.feature.pullrequest

import com.otb.githubtracker.network.ApiResult
import com.otb.githubtracker.network.getResult
import com.otb.githubtracker.network.service.GitHubApiService
import javax.inject.Inject

/**
 * Created by Mohit Rajput on 06/08/22.
 */
class PullRequestRepository @Inject constructor(private val gitHubApiService: GitHubApiService) :
    PullRequestContact.Repository {

    private suspend fun fetchPullRequests(
        organizationName: String,
        repositoryName: String,
        state: PullRequestModels.PullRequestState,
        page: Int
    ): ApiResult<List<PullRequestModels.PullRequestResponse>> {
        return getResult {
            gitHubApiService.fetchPullRequests(
                organizationName, repositoryName, state.key, page
            )
        }
    }

    override suspend fun fetchClosedPullRequests(
        organizationName: String,
        repositoryName: String,
        page: Int
    ) = fetchPullRequests(
        organizationName,
        repositoryName,
        PullRequestModels.PullRequestState.Close,
        page
    )
}