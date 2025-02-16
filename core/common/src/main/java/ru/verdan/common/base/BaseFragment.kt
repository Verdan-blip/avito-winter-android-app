package ru.verdan.common.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import ru.verdan.common.base.mvvm.BaseEvent
import ru.verdan.common.util.collectWithLifecycle

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    @LayoutRes id: Int
) : Fragment(id) {

    protected abstract val viewBinding: VB

    protected abstract val viewModel: VM

    protected fun showSnackBar(message: String) {
        Snackbar.make(requireContext(), viewBinding.root, message, Snackbar.LENGTH_LONG)
            .show()
    }

    protected fun collectBaseEvents() {
        viewModel.baseEvent.collectWithLifecycle(viewLifecycleOwner) { event ->
            when (event) {
                is BaseEvent.ShowSnackbar -> {
                    showSnackBar(event.message)
                }
            }
        }
    }
}
