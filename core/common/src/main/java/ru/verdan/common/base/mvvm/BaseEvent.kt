package ru.verdan.common.base.mvvm

sealed interface BaseEvent {

    data class ShowSnackbar(val message: String) : BaseEvent
}
