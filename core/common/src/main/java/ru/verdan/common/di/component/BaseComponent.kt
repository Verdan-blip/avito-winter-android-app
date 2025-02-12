package ru.verdan.common.di.component

import ru.verdan.common.base.BaseViewModel

interface BaseComponent {

    val viewModelFactory: BaseViewModel.Companion.BaseFactory
}
