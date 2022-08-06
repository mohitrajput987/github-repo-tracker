package com.otb.githubtracker.feature.pullrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otb.githubtracker.databinding.ItemClosedPullRequestBinding

/**
 * Created by Mohit Rajput on 06/08/22.
 */
class PullRequestAdapter :
    ListAdapter<PullRequestModels.PullRequestEntity, PullRequestAdapter.ViewHolder>(DIFF_UTIL) {
    companion object {
        private val DIFF_UTIL =
            object : DiffUtil.ItemCallback<PullRequestModels.PullRequestEntity>() {
                override fun areItemsTheSame(
                    oldItem: PullRequestModels.PullRequestEntity,
                    newItem: PullRequestModels.PullRequestEntity
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: PullRequestModels.PullRequestEntity,
                    newItem: PullRequestModels.PullRequestEntity
                ): Boolean {
                    return oldItem.title == newItem.title && oldItem.createdAt ==
                            newItem.createdAt && oldItem.closedAt == newItem.closedAt && oldItem.user.id == newItem.user.id
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemClosedPullRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemClosedPullRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pullRequestEntity: PullRequestModels.PullRequestEntity) {
            binding.pullRequest = pullRequestEntity
            binding.executePendingBindings()
        }
    }
}