package ru.verdan.feature.home.di

import dagger.Component
import ru.verdan.common.di.component.BaseComponent
import ru.verdan.common.di.scopes.FeatureScope

@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [HomeComponentDependencies::class]
)
interface HomeComponent : BaseComponent {

    @Component.Factory
    interface Factory {

        fun create(
            dependencies: HomeComponentDependencies
        ): HomeComponent
    }
}
