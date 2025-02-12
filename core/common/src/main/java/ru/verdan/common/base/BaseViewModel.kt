package ru.verdan.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.verdan.common.R
import ru.verdan.common.base.mvvm.BaseEvent
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.common.util.ViewModelFactories
import javax.inject.Inject

abstract class BaseViewModel(
    protected val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _baseEvent = MutableSharedFlow<BaseEvent>()
    val baseEvent: SharedFlow<BaseEvent> get() = _baseEvent

    protected suspend inline fun doSafeCall(
        onErrorHandler: (Exception) -> String,
        body: () -> Unit
    ) {
        try {
            body()
        } catch (ex: Exception) {
            emitEvent(BaseEvent.ShowSnackbar(onErrorHandler(ex)))
        }
    }

    protected suspend inline fun doSafeCall(
        body: () -> Unit
    ) {
        try {
            body()
        } catch (ex: Exception) {
            emitEvent(BaseEvent.ShowSnackbar(
                resourceProvider.getString(R.string.unknown_error)
            ))
        }
    }

    suspend fun emitEvent(event: BaseEvent) {
        _baseEvent.emit(event)
    }

    companion object {

        class BaseFactory @Inject constructor(
            private val viewModelFactories: ViewModelFactories
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelFactories.getValue(modelClass).get() as T
            }
        }
    }
}
