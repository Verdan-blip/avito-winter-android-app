package ru.verdan.feature

import ru.verdan.feature.home.di.HomeComponentDependencies
import ru.verdan.feature.loaded.di.LoadedTracksDependencies
import ru.verdan.feature.trackplayer.di.TrackPlayerDependencies

interface FeatureDependencies :
        HomeComponentDependencies,
        TrackPlayerDependencies,
        LoadedTracksDependencies
