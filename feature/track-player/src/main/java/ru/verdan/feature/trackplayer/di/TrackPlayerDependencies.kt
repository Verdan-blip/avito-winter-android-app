package ru.verdan.feature.trackplayer.di

import android.content.Context
import retrofit2.Retrofit
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.local.api.repository.TrackRepository

interface TrackPlayerDependencies : ComponentDependencies {
    val retrofit: Retrofit
    val context: Context
    val trackRepository: TrackRepository
}
