package ru.verdan.feature.home.di

import dagger.Module
import ru.verdan.common.di.module.CommonModule
import ru.verdan.feature.home.data.di.HomeDataModule
import ru.verdan.feature.home.domain.di.HomeDomainModule
import ru.verdan.feature.home.presentation.di.HomePresentationModule

@Module(
    includes = [
        HomeDataModule::class,
        HomeDomainModule::class,
        HomePresentationModule::class,
        CommonModule::class
    ]
)
interface HomeModule
