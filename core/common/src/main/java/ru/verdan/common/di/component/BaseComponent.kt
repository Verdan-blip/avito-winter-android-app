package ru.verdan.common.di.component

import ru.verdan.common.base.BaseViewModel

interface BaseComponent : DiComponent {

    val viewModelFactory: BaseViewModel.Companion.Factory
}
