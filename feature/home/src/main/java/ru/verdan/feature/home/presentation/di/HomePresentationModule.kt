package ru.verdan.feature.home.presentation.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.verdan.common.di.key.ViewModelKey
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.home.presentation.HomeViewModel

@Module
internal interface HomePresentationModule {

    @FeatureScope
    @[Binds IntoMap ViewModelKey(HomeViewModel::class)]
    fun bindsViewModel(homeViewModel: HomeViewModel): ViewModel
}
