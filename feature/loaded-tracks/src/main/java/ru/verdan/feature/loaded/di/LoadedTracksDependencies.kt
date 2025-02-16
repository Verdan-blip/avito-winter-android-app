package ru.verdan.feature.loaded.di

import android.content.Context
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.feature.loaded.presention.LoadedTracksRouter
import ru.verdan.local.api.repository.TrackLocalRepository

interface LoadedTracksDependencies : ComponentDependencies {
    val loadedTrackRouter: LoadedTracksRouter
    val trackLocalRepository: TrackLocalRepository
    val context: Context
}
