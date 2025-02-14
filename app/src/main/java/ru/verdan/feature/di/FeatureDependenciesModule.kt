package ru.verdan.feature.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.common.di.key.ComponentDependenciesKey
import ru.verdan.di.AppComponent
import ru.verdan.feature.home.di.HomeComponentDependencies
import ru.verdan.feature.home.di.HomeDependenciesModule

@Module(
    includes = [
        HomeDependenciesModule::class
    ]
)
interface FeatureDependenciesModule {

    @[Binds IntoMap ComponentDependenciesKey(HomeComponentDependencies::class)]
    fun bindsHomeDependencies(appComponent: AppComponent): ComponentDependencies
}
