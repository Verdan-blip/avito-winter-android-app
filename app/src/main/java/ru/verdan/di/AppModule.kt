package ru.verdan.di

import dagger.Module
import ru.verdan.common.di.module.CommonModule
import ru.verdan.local.impl.di.LocalModule
import ru.verdan.network.impl.di.NetworkModule
import ru.verdan.player.impl.di.PlayerModule

@Module(
    includes = [
        CommonModule::class,
        LocalModule::class,
        NetworkModule::class,
        PlayerModule::class,
        FeatureDependenciesModule::class
    ]
)
interface AppModule
