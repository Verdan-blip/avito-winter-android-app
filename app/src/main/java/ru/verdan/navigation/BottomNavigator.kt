package ru.verdan.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class BottomNavigator @Inject constructor() : Navigator {

    private var navController: NavController? = null

    override fun getNavController(): NavController? {
        return navController
    }

    override fun attachNavController(navController: NavController) {
        this.navController = navController
    }

    override fun detachNavController(navController: NavController) {
        this.navController = navController
    }
}
