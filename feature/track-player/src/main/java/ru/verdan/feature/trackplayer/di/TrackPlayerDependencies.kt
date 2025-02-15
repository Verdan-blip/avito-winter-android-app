package ru.verdan.feature.trackplayer.di

import android.content.Context
import retrofit2.Retrofit
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.player.controller.Controller

interface TrackPlayerDependencies : ComponentDependencies {
    val retrofit: Retrofit
    val controller: Controller
    val context: Context
}
