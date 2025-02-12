package ru.verdan.feature.home.presentation.di

import dagger.Module
import dagger.multibindings.IntoMap
import ru.verdan.common.base.BaseViewModel
import ru.verdan.common.di.key.ViewModelKey
import ru.verdan.feature.home.presentation.HomeViewModel

@Module
internal interface HomePresentationModule {

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideViewModel(homeViewModel: HomeViewModel): BaseViewModel
}
