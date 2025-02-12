package ru.verdan.feature.home.di

import dagger.Module
import ru.verdan.feature.home.domain.di.HomeDomainModule
import ru.verdan.feature.home.presentation.di.HomePresentationModule

@Module(
    includes = [
        HomeDomainModule::class,
        HomePresentationModule::class
    ]
)
interface HomeModule
