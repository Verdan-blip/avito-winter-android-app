package ru.verdan.feature.home

import ru.verdan.feature.home.presentation.HomeRouter
import ru.verdan.feature.trackplayer.presentation.TrackPlayerFragmentDirections
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.di.Root
import javax.inject.Inject

class HomeRouterImpl @Inject constructor(
    @Root private val navigator: Navigator
) : HomeRouter {

    override fun navigateToPlayer(trackIds: List<Long>) {
        val action = TrackPlayerFragmentDirections
            .actionGlobalTrackPlayerFragment(trackIds.toLongArray())
        navigator.getNavController()?.navigate(action)
    }
}
