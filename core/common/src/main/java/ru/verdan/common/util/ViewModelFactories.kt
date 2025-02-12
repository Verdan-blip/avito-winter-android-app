package ru.verdan.common.util

import androidx.lifecycle.ViewModel
import javax.inject.Provider


typealias ViewModelFactories = Map<
        Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>
