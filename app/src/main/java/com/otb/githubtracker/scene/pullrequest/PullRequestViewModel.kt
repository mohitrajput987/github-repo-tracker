package com.otb.githubtracker.scene.pullrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otb.githubtracker.network.ApiResult
import com.otb.githubtracker.network.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mohit Rajput on 06/08/22.
 */

@HiltViewModel
class PullRequestViewModel @Inject constructor(private val repository: PullRequestContact.Repository) :
    ViewModel() {
    private val _pullRequestLiveData =
        MutableLiveData<ViewState<List<PullRequestModels.PullRequestEntity>>>()
    val pullRequestLiveData: LiveData<ViewState<List<PullRequestModels.PullRequestEntity>>> get() = _pullRequestLiveData

    private val pullRequestMapper = PullRequestMapper()
    private var pullRequestsPageNum = 1

    fun fetchClosedPullRequests(
        organizationName: String,
        repositoryName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result =
                repository.fetchClosedPullRequests(
                    organizationName,
                    repositoryName,
                    pullRequestsPageNum
                )) {
                is ApiResult.Success -> {
                    withContext(Dispatchers.Main) {
                        val pullRequestEntities = pullRequestMapper.mapFrom(result.data)
                        _pullRequestLiveData.value = ViewState.Success(pullRequestEntities)
                    }
                }
                is ApiResult.Error -> {
                    withContext(Dispatchers.Main) {
                        _pullRequestLiveData.value = ViewState.Error(result.message)
                    }
                }
            }
        }
    }
}