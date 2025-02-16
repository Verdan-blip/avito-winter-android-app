package ru.verdan.feature.loaded.presention.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.verdan.common.di.key.ViewModelKey
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.loaded.presention.LoadedTracksViewModel

@Module
internal interface LoadedTracksPresentationModule {

    @FeatureScope
    @[Binds IntoMap ViewModelKey(LoadedTracksViewModel::class)]
    fun bindsViewModel(viewModel: LoadedTracksViewModel): ViewModel
}
