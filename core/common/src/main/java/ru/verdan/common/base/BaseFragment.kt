package ru.verdan.common.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(@LayoutRes id: Int, ) : Fragment(id) {

    protected abstract val viewBinding: VB
}
