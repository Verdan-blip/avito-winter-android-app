package ru.verdan.feature.home

import android.os.Bundle
import ru.verdan.feature.home.presentation.HomeRouter
import ru.verdan.feature.trackplayer.presentation.TrackPlayerFragment
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.di.Root
import javax.inject.Inject

class HomeRouterImpl @Inject constructor(
    @Root private val navigator: Navigator
) : HomeRouter {

    override fun navigateToPlayer(trackIds: List<Long>) {
        navigator.getNavController()?.navigate(
            resId = ru.verdan.R.id.action_global_trackPlayerFragment,
            args = Bundle().apply {
                putLongArray(TrackPlayerFragment.KEY_QUEUE_TRACK_IDS, trackIds.toLongArray())
            }
        )
    }
}
