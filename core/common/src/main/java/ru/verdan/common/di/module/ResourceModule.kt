package ru.verdan.common.di.module

import dagger.Binds
import dagger.Module
import ru.verdan.common.resource.ResourceProvider
import ru.verdan.common.resource.ResourceProviderImpl

@Module
internal interface ResourceModule {

    @Binds
    fun bindsResourceProvider(
        resourceProviderImpl: ResourceProviderImpl
    ): ResourceProvider
}
