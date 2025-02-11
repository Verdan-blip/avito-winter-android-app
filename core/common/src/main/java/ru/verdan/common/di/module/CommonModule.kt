package ru.verdan.common.di.module

import dagger.Module

@Module(
    includes = [
        CoroutinesModule::class
    ]
)
interface CommonModule
