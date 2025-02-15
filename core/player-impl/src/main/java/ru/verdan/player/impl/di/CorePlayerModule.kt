package ru.verdan.player.impl.di

import android.content.ComponentName
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import ru.verdan.common.di.module.ApplicationCoroutineScope
import ru.verdan.player.controller.Controller
import ru.verdan.player.impl.controller.ControllerImpl
import ru.verdan.player.impl.presentation.PlayerService

@Module
class CorePlayerModule {

    @Provides
    fun provideExoPlayer(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context)
            .build()
    }

    @Provides
    fun provideMediaSession(context: Context, player: ExoPlayer): MediaSession {
        return MediaSession.Builder(context, player)
            .build()
    }

    @Provides
    fun provideSessionToken(context: Context): SessionToken =
        SessionToken(context, ComponentName(context, PlayerService::class.java))

    @Provides
    fun provideMediaControllerFuture(
        context: Context,
        sessionToken: SessionToken
    ): ListenableFuture<MediaController> {
        return MediaController.Builder(context, sessionToken)
            .buildAsync()
    }

    @Provides
    fun provideController(
        mediaControllerFuture: ListenableFuture<MediaController>,
        @ApplicationCoroutineScope applicationScope: CoroutineScope
    ): Controller {
        return ControllerImpl(mediaControllerFuture, applicationScope)
    }
}
