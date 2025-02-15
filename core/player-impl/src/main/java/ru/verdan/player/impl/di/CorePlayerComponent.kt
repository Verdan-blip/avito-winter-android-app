package ru.verdan.player.impl.di

import dagger.Component
import ru.verdan.common.di.component.DiComponent
import ru.verdan.common.di.scopes.ServiceScope
import ru.verdan.player.impl.presentation.PlayerService

@ServiceScope
@Component(
    modules = [CorePlayerModule::class],
    dependencies = [CorePlayerComponentDependencies::class]
)
interface CorePlayerComponent : DiComponent {

    fun inject(service: PlayerService)

    @Component.Factory
    interface Factory {

        fun create(dependencies: CorePlayerComponentDependencies): CorePlayerComponent
    }
}
