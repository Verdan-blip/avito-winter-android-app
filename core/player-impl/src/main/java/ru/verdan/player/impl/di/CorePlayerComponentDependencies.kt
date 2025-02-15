package ru.verdan.player.impl.di

import android.content.Context
import ru.verdan.common.di.component.ComponentDependencies

interface CorePlayerComponentDependencies : ComponentDependencies {
    val context: Context
}
