package com.bironader.musicsearch.framework.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bironader.musicsearch.framework.utils.ErrorTypes

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!
    protected abstract fun bindViews()
    protected open fun observe() {
        /*mvvm fragment override this method to observe livedata*/
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observe()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        bindViews()
        return binding.root
    }


    protected fun handleErrors(error: ErrorTypes) {
//        SnackbarUtils.with(binding.root)
//            .setMessage(error.getMessage(requireContext()))
    }


    // avoid leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}