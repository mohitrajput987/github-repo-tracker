package com.otb.githubtracker.scene.pullrequest

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Mohit Rajput on 06/08/22.
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
interface PullRequestModule {
    @Binds
    fun bindPullRequestRepository(pullRequestRepository: PullRequestRepository): PullRequestContact.Repository
}