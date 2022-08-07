package com.otb.githubtracker.feature.pullrequest

import com.otb.githubtracker.network.ApiResult

/**
 * Created by Mohit Rajput on 06/08/22.
 */
class PullRequestContact {
    interface Repository{
        suspend fun fetchClosedPullRequests(organizationName: String, repositoryName: String, page: Int) : ApiResult<List<PullRequestModels.PullRequestResponse>>
    }
}