package ru.verdan.common.resource

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

internal class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun getString(@StringRes id: Int): String = context.getString(id)
}
