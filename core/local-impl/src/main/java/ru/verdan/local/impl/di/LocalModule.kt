package ru.verdan.local.impl.di

import dagger.Module

@Module(
    includes = [
        LocalDataSourceModule::class,
        LocalRepositoryModule::class,
        LocalDatabaseModule::class
    ]
)
interface LocalModule
