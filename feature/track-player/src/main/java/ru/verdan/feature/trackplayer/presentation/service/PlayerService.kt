package ru.verdan.feature.trackplayer.presentation.service

import android.content.Intent
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import ru.verdan.feature.trackplayer.di.TrackPlayerComponentHolder
import javax.inject.Inject

class PlayerService : MediaSessionService() {

    @Inject lateinit var mediaSession: MediaSession

    @Inject lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        TrackPlayerComponentHolder.create(this)
            .inject(this)
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        mediaSession.apply {
            if (!player.playWhenReady || player.mediaItemCount == 0) {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
    }

    override fun onDestroy() {
        mediaSession.apply {
            player.release()
            release()
        }
        super.onDestroy()
    }
}
