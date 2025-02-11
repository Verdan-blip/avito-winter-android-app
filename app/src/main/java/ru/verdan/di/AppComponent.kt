package ru.verdan.di

import dagger.BindsInstance
import dagger.Component
import ru.verdan.App
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: App): AppComponent
    }
}
