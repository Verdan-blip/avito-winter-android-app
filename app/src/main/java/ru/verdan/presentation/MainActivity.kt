package ru.verdan.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.verdan.App
import ru.verdan.R
import ru.verdan.common.base.BaseActivity
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.di.Root
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject @Root lateinit var navigator: Navigator

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdgeAndSetupInsets()

        (application as App).appComponent
            .inject(this)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.fcv_root) as NavHostFragment

        navController = navHost.navController.also { controller ->
            navigator.attachNavController(controller)
            navController = controller
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.also { controller ->
            navigator.detachNavController(controller)
            navController = null
        }
    }

    private fun enableEdgeToEdgeAndSetupInsets() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fcv_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
