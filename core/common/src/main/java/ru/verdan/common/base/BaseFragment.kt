package ru.verdan.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.verdan.common.di.component.BaseComponent
import ru.verdan.common.di.holder.ComponentHolder

abstract class BaseFragment<
        VB : ViewBinding,
        H : ComponentHolder<out BaseComponent>>(
    @LayoutRes id: Int,
    protected val componentHolder: H
) : Fragment(id) {

    protected abstract val viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentHolder.create(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        componentHolder.release()
    }
}
