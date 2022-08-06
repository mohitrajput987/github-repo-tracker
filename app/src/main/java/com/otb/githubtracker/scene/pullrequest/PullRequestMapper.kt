package com.otb.githubtracker.scene.pullrequest

import com.otb.githubtracker.common.Mapper
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mohit Rajput on 06/08/22.
 */
class PullRequestMapper :
    Mapper<List<PullRequestModels.PullRequestResponse>, List<PullRequestModels.PullRequestEntity>> {
    override fun mapFrom(from: List<PullRequestModels.PullRequestResponse>): List<PullRequestModels.PullRequestEntity> {
        return from.map {
            val user = PullRequestModels.UserEntity(it.user.id, it.user.userName, it.user.avatarUrl)
            PullRequestModels.PullRequestEntity(
                id = it.id,
                title = it.title,
                createdAt = getFormattedDate(it.createdAt),
                closedAt = it.closedAt?.let { closedAt -> getFormattedDate(closedAt) } ?: "",
                user = user
            )
        }
    }

    private fun getFormattedDate(date: Date) =
        SimpleDateFormat("dd/MMM/yyyy hh:mm a", Locale.ENGLISH).format(date)
}