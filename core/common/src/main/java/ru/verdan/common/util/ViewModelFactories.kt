package ru.verdan.common.util

import androidx.lifecycle.ViewModel
import javax.inject.Provider


typealias ViewModelProviders = Map<
        Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>
