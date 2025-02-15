package ru.verdan.feature.trackplayer.di

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.feature.trackplayer.presentation.service.controller.Controller

interface TrackPlayerDependencies : ComponentDependencies {
    val retrofit: Retrofit
    val context: Context
}
