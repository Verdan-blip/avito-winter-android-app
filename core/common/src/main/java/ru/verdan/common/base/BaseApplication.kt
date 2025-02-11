package ru.verdan.common.base

import android.app.Application
import ru.verdan.common.di.container.ComponentDependenciesContainer

abstract class BaseApplication : Application(), ComponentDependenciesContainer
