package ru.verdan.feature.loaded

import android.os.Bundle
import ru.verdan.feature.loaded.presention.LoadedTracksRouter
import ru.verdan.feature.trackplayer.presentation.TrackPlayerFragment
import ru.verdan.navigation.Navigator
import ru.verdan.navigation.di.Root
import javax.inject.Inject

class LoadedTracksRouterImpl @Inject constructor(
    @Root private val navigator: Navigator
) : LoadedTracksRouter {

    override fun navigateToPlayer(trackIds: List<Long>, selectedTrackId: Long) {
        navigator.getNavController()?.navigate(
            resId = ru.verdan.R.id.action_global_trackPlayerFragment,
            args = Bundle().apply {
                putLongArray(TrackPlayerFragment.KEY_QUEUE_TRACK_IDS, trackIds.toLongArray())
                putLong(TrackPlayerFragment.KEY_SELECTED_TRACK, selectedTrackId)
            }
        )
    }
}
