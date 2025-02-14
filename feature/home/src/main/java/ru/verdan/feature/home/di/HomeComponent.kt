package ru.verdan.feature.home.di

import dagger.Component
import ru.verdan.common.di.component.BaseComponent
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.home.presentation.HomeFragment

@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [HomeComponentDependencies::class]
)
interface HomeComponent : BaseComponent {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {

        fun create(dependencies: HomeComponentDependencies): HomeComponent
    }
}
