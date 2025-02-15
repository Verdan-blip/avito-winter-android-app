package ru.verdan.core.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.common.di.key.ComponentDependenciesKey
import ru.verdan.di.AppComponent
import ru.verdan.player.impl.di.CorePlayerComponentDependencies

@Module
interface CoreDependenciesModule {

    @[Binds IntoMap ComponentDependenciesKey(CorePlayerComponentDependencies::class)]
    fun bindsPlayerDependencies(appComponent: AppComponent): ComponentDependencies
}
