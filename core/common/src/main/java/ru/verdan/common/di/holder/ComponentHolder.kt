package ru.verdan.common.di.holder

import android.content.Context
import ru.verdan.common.di.component.DiComponent

abstract class ComponentHolder<T : DiComponent> {

    private var component: T? = null

    protected abstract fun init(context: Context): T

    fun create(context: Context): T {
        return init(context).also { component = it }
    }

    fun release() {
        component = null
    }
}
