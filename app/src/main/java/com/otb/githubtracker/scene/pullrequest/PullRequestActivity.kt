package com.otb.githubtracker.scene.pullrequest

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.otb.githubtracker.common.BaseActivity
import com.otb.githubtracker.databinding.ActivityPullRequestBinding
import com.otb.githubtracker.network.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullRequestActivity : BaseActivity<ActivityPullRequestBinding>() {
    companion object {
        private const val DEFAULT_ORG_NAME = "mohitrajput987"
        private const val DEFAULT_REPO_NAME = "github-repo-tracker"
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityPullRequestBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PullRequestViewModel>()
    private val pullRequestAdapter by lazy { PullRequestAdapter() }

    override fun setupView() {
        setupRecyclerView()
        observeClosePullRequests()
        viewModel.fetchClosedPullRequests(DEFAULT_ORG_NAME, DEFAULT_REPO_NAME)
    }

    private fun setupRecyclerView() {
        val verticalLayoutManager = LinearLayoutManager(this@PullRequestActivity)
        binding.rvPullRequests.apply {
            layoutManager = verticalLayoutManager
            adapter = pullRequestAdapter
            addItemDecoration(DividerItemDecoration(baseContext, verticalLayoutManager.orientation))
        }
    }

    private fun observeClosePullRequests() {
        viewModel.pullRequestLiveData.observe(this) {
            when (it) {
                is ViewState.Loading -> showLoadingSpinner()
                is ViewState.Success -> showPullRequests(it.data)
                is ViewState.Error -> showErrorMessage(it.errorMessage)
            }
        }
    }

    private fun showPullRequests(pullRequests: List<PullRequestModels.PullRequestEntity>) {
        dismissLoadingSpinner()
        pullRequestAdapter.submitList(pullRequests)
    }
}