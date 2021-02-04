package io.stark.locale.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/** Created by Jahongir on 04/02/2021.*/
abstract class BindingFragment<VB : ViewBinding> : Fragment() {

    private var bindingHolder: VB? = null
    protected val binding: VB get() = bindingHolder!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingHolder = createBinding(inflater, container)
        return binding.root
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    override fun onDestroyView() {
        bindingHolder = null
        super.onDestroyView()
    }

    open fun setupViews() {}

    open fun setupObservers() {}

}