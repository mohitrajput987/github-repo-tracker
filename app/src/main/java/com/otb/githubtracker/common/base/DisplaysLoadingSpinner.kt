package com.otb.githubtracker.common.base

import com.otb.githubtracker.common.LoadingSpinner

/**
 * Created by Mohit Rajput on 06/08/22.
 */
interface DisplaysLoadingSpinner {
    var loadingSpinner: LoadingSpinner

    fun showLoadingSpinner() {
        loadingSpinner.show()
    }

    fun dismissLoadingSpinner() {
        loadingSpinner.dismiss()
    }
}