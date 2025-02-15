package ru.verdan.di

import android.content.Context
import dagger.Binds
import dagger.Module
import ru.verdan.App
import ru.verdan.common.di.module.CommonModule
import ru.verdan.core.di.CoreDependenciesModule
import ru.verdan.feature.di.FeatureDependenciesModule
import ru.verdan.local.impl.di.LocalModule
import ru.verdan.navigation.di.NavigationModule
import ru.verdan.network.impl.di.NetworkModule
import ru.verdan.player.impl.di.CorePlayerModule

@Module(
    includes = [
        CommonModule::class,
        LocalModule::class,
        NetworkModule::class,
        CorePlayerModule::class,
        NavigationModule::class,
        FeatureDependenciesModule::class,
        CoreDependenciesModule::class
    ]
)
interface AppModule {

    @Binds
    fun provideContext(app: App): Context
}
