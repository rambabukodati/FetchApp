package com.example.fetchapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


/* For scalability creared base fragment with basic functionality of fragment */
abstract class BaseFragment<T : ViewBinding> (@LayoutRes val layoutResId: Int) : Fragment(layoutResId) {

    private lateinit var mRootView: View

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        mRootView = binding.root
        mRootView.isClickable = true
        mRootView.isFocusable = true
       return  mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragementUI(mRootView)
    }

    abstract fun setupFragementUI(view: View?)

    override fun onDestroyView() {
        if(mRootView.parent != null) {
            (mRootView.parent as ViewGroup).removeView(mRootView)
        }
        super.onDestroyView()
    }

}