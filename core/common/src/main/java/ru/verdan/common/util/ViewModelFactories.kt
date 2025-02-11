package ru.verdan.common.util

import ru.verdan.common.base.BaseViewModel
import javax.inject.Provider


typealias ViewModelFactories = Map<
        Class<out BaseViewModel>,
        @JvmSuppressWildcards Provider<BaseViewModel>>
