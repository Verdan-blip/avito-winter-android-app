package ru.verdan

import ru.verdan.common.base.BaseApplication
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.common.di.component.ComponentDependencyProvider
import ru.verdan.di.AppComponent
import ru.verdan.di.DaggerAppComponent
import javax.inject.Inject

class App : BaseApplication() {

    @Inject lateinit var dependencyProvider: ComponentDependencyProvider

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this)
        appComponent.inject(this)
    }

    override fun <T : ComponentDependencies> getDependencies(key: Class<out T>): T {
        return dependencyProvider.provide(key)
    }
}
