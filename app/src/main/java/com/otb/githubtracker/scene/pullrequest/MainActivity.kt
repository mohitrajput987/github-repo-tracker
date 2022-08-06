package com.otb.githubtracker.scene.pullrequest

import android.view.LayoutInflater
import com.otb.githubtracker.common.BaseActivity
import com.otb.githubtracker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun setupView() {

    }
}