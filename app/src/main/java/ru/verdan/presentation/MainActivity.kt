package ru.verdan.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dev.androidbroadcast.vbpd.viewBinding
import ru.verdan.App
import ru.verdan.R
import ru.verdan.common.base.BaseActivity
import ru.verdan.databinding.ActivityMainBinding
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.di.Root
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    @Inject @Root lateinit var navigator: Navigator

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeAndSetupInsets()

        setContentView(R.layout.activity_main)

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
        //enableEdgeToEdge()
        /*ViewCompat.setOnApplyWindowInsetsListener(viewBinding.fcvRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}
