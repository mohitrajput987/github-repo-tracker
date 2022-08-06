package com.otb.githubtracker.scene.pullrequest

import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
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

    override fun setupView() {
        observeClosePullRequests()
        viewModel.fetchClosedPullRequests(DEFAULT_ORG_NAME, DEFAULT_REPO_NAME)
    }

    private fun observeClosePullRequests() {
        viewModel.pullRequestLiveData.observe(this) {
            when (it) {
                is ViewState.Loading -> displayLoader()
                is ViewState.Success -> displayPullRequests(it.data)
                is ViewState.Error -> displayError(it.errorMessage)
            }
        }
    }

    private fun displayLoader() {

    }

    private fun hideLoader() {

    }

    private fun displayError(errorMessage: String) {
        hideLoader()
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun displayPullRequests(pullRequests: List<PullRequestModels.PullRequestEntity>) {
        hideLoader()
    }
}