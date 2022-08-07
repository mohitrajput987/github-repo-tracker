package com.otb.githubtracker.feature.pullrequest

import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.otb.githubtracker.common.LoadingSpinner
import com.otb.githubtracker.common.base.BaseActivity
import com.otb.githubtracker.common.base.DisplaysLoadingSpinner
import com.otb.githubtracker.databinding.ActivityPullRequestBinding
import com.otb.githubtracker.network.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullRequestActivity : BaseActivity<ActivityPullRequestBinding>(), DisplaysLoadingSpinner {
    companion object {
        private const val DEFAULT_ORG_NAME = "square"
        private const val DEFAULT_REPO_NAME = "retrofit"
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityPullRequestBinding.inflate(layoutInflater)

    private val viewModel by viewModels<PullRequestViewModel>()
    private val pullRequestAdapter by lazy { PullRequestAdapter() }
    override val loadingSpinner by lazy { LoadingSpinner(this) }

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

    private fun showErrorMessage(errorMessage: String) {
        dismissLoadingSpinner()
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showPullRequests(pullRequests: List<PullRequestModels.PullRequestEntity>) {
        dismissLoadingSpinner()
        pullRequestAdapter.submitList(pullRequests)
    }
}