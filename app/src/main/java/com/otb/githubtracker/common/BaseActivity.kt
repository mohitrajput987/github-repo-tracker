package com.otb.githubtracker.common

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.otb.githubtracker.common.base.DisplaysErrorMessage
import com.otb.githubtracker.common.base.DisplaysLoadingSpinner

/**
 * Created by Mohit Rajput on 06/08/22.
 * Base class for all activities of app which mainly fulfill the purpose of creating and destroying
 * binding.
 * Child activity need to implement inflateLayout() with specific binding.
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), DisplaysLoadingSpinner,
    DisplaysErrorMessage {

    private var _binding: ViewBinding? = null
    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB
    override lateinit var loadingSpinner: LoadingSpinner

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateLayout(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        loadingSpinner = LoadingSpinner(this)
        setupView()
    }

    abstract fun setupView()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showErrorMessage(errorMessage: String) {
        dismissLoadingSpinner()
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
    }
}