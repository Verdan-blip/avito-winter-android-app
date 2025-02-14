package ru.verdan.navigation

import androidx.navigation.NavController

interface Navigator {

    fun getNavController(): NavController?

    fun attachNavController(navController: NavController)

    fun detachNavController(navController: NavController)
}
