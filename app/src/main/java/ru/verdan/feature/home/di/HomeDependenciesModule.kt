package ru.verdan.feature.home.di

import dagger.Binds
import dagger.Module
import ru.verdan.feature.home.HomeRouterImpl
import ru.verdan.feature.home.presentation.HomeRouter

@Module
interface HomeDependenciesModule {

    @Binds
    fun bindsHomeRouter(homeRouterImpl: HomeRouterImpl): HomeRouter
}
