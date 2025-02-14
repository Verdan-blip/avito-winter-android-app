package ru.verdan.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.verdan.App
import ru.verdan.R
import ru.verdan.databinding.FragmentBottomNavigationBinding
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.di.Bottom
import javax.inject.Inject

class BottomNavigationFragment : Fragment() {

    //private val viewBinding by viewBinding(FragmentBottomNavigationBinding::bind)

    private var viewBinding: FragmentBottomNavigationBinding? = null

    @Inject @Bottom lateinit var navigator: Navigator

    private var navController: NavController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager
            .findFragmentById(R.id.fcv_bottom) as NavHostFragment

        navController = navHost.navController.also { controller ->
            navigator.attachNavController(controller)
            navController = controller
        }

        navController?.also { controller ->
            viewBinding?.bnv?.setupWithNavController(controller)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.also { controller ->
            navigator.detachNavController(controller)
            navController = null
        }
    }
}
