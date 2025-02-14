package ru.verdan.feature.home.di

import android.content.Context
import retrofit2.Retrofit
import ru.verdan.common.di.component.ComponentDependencies
import ru.verdan.feature.home.presentation.HomeRouter

interface HomeComponentDependencies : ComponentDependencies {
    val homeRouter: HomeRouter
    val retrofit: Retrofit
    val context: Context
}
