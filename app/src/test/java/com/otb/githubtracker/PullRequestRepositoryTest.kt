package com.otb.githubtracker

import com.otb.githubtracker.feature.pullrequest.PullRequestModels
import com.otb.githubtracker.feature.pullrequest.PullRequestRepository
import com.otb.githubtracker.network.ApiResult
import com.otb.githubtracker.network.service.GitHubApiService
import com.otb.githubtracker.util.mockRetrofitErrorResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import java.util.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class PullRequestRepositoryTest {

    @MockK
    private lateinit var gitHubApiService: GitHubApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
    }

    @Test
    fun `success result should be returned on pull request api success`() = runTest {
        val date = Date()
        val user = PullRequestModels.UserResponse(id = 1, "userName", "avatar url", "admin")
        val pullRequestsResponse = listOf(
            PullRequestModels.PullRequestResponse(
                id = 1,
                "title",
                "url",
                date,
                date,
                date,
                user
            )
        )
        coEvery {
            gitHubApiService.fetchPullRequests(any(), any(), any(), any(), any())
        } returns Response.success(pullRequestsResponse)

        val repository = PullRequestRepository(gitHubApiService)
        val result = repository.fetchClosedPullRequests(
            organizationName = "mohitrajput987",
            repositoryName = "github-repo-tracker",
            page = 1
        )
        assert(result is ApiResult.Success)
        assertEquals(pullRequestsResponse, (result as ApiResult.Success).data)
    }

    @Test
    fun `error result should be returned on pull request api failure`() = runTest {
        coEvery {
            gitHubApiService.fetchPullRequests(any(), any(), any(), any(), any())
        } returns mockRetrofitErrorResponse()

        val repository = PullRequestRepository(gitHubApiService)
        val result = repository.fetchClosedPullRequests(
            organizationName = "",
            repositoryName = "github-repo-tracker",
            page = 0
        )
        assert(result is ApiResult.Error)
    }
}
