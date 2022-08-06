package com.otb.githubtracker.scene.pullrequest

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Mohit Rajput on 05/08/22.
 */
class PullRequestModels {
    data class PullRequestEntity(
        val id: Long,
        val title: String,
        val createdAt: String,
        val closedAt: String,
        val user: UserEntity
    )

    data class UserEntity(
        val id: Long,
        val userName: String,
        val imageUrl: String
    )

    @Keep
    data class PullRequestResponse(
        @SerializedName("id")
        val id: Long,

        @SerializedName("title")
        val title: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("created_at")
        val createdAt: Date,

        @SerializedName("closed_at")
        val closedAt: Date?,

        @SerializedName("updated_at")
        val updatedAt: Date,

        @SerializedName("user")
        val user: UserResponse
    )

    @Keep
    data class UserResponse(
        @SerializedName("id")
        val id: Long,

        @SerializedName("login")
        val userName: String,

        @SerializedName("avatar_url")
        val avatarUrl: String,

        @SerializedName("type")
        val type: String,
    )

    enum class PullRequestState(val key: String) {
        Open("open"), Close("close")
    }
}