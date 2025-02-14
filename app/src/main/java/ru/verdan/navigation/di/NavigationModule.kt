package ru.verdan.navigation.di

import dagger.Binds
import dagger.Module
import ru.verdan.navigation.BottomNavigator
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.RootNavigator
import javax.inject.Qualifier

@Module
interface NavigationModule {

    @Root
    @Binds
    fun bindsRootNavigator(rootNavigator: RootNavigator): Navigator

    @Bottom
    @Binds
    fun bindsBottomNavigator(rootNavigator: BottomNavigator): Navigator
}

@Qualifier
annotation class Root

@Qualifier
annotation class Bottom
