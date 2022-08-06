package com.otb.githubtracker.scene.pullrequest

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Mohit Rajput on 05/08/22.
 */
class PullRequestModels {
    @Keep
    data class PullRequestResponse(
        @SerializedName("id")
        val id : Long
    )
}