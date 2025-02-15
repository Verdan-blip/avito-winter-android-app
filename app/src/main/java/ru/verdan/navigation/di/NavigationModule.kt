package ru.verdan.navigation.di

import dagger.Binds
import dagger.Module
import ru.verdan.common.di.scopes.ApplicationScope
import ru.verdan.navigation.BottomNavigator
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.RootNavigator
import javax.inject.Qualifier

@Module
interface NavigationModule {

    @ApplicationScope
    @Root
    @Binds
    fun bindsRootNavigator(rootNavigator: RootNavigator): Navigator

    @ApplicationScope
    @Bottom
    @Binds
    fun bindsBottomNavigator(rootNavigator: BottomNavigator): Navigator
}

@Qualifier
annotation class Root

@Qualifier
annotation class Bottom
