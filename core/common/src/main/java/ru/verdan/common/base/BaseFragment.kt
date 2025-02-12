package ru.verdan.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import ru.verdan.common.di.component.BaseComponent
import ru.verdan.common.di.holder.ComponentHolder

abstract class BaseFragment<
        VB : ViewBinding,
        H : ComponentHolder<out BaseComponent>>(
    protected val componentHolder: H
) : Fragment() {

    protected abstract val viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentHolder.create(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        componentHolder.release()
    }

    protected inline fun <reified VM : BaseViewModel> daggerViewModel(): Lazy<VM> {
        return viewModels {
            componentHolder
                .create(requireContext())
                .viewModelFactory
        }
    }
}
