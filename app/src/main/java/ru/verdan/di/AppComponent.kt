package ru.verdan.di

import dagger.BindsInstance
import dagger.Component
import ru.verdan.App
import ru.verdan.common.di.scopes.ApplicationScope
import ru.verdan.feature.FeatureDependencies
import ru.verdan.presentation.BottomNavigationFragment
import ru.verdan.presentation.MainActivity

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent : FeatureDependencies {

    fun inject(app: App)

    fun inject(activity: MainActivity)

    fun inject(fragment: BottomNavigationFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: App): AppComponent
    }
}
