package ru.verdan.feature.trackplayer.presentation.di

import android.content.ComponentName
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import ru.verdan.common.di.scopes.FeatureScope
import ru.verdan.feature.trackplayer.presentation.service.PlayerService

@Module
internal class TrackPlayerSessionModule {

    @FeatureScope
    @Provides
    fun provideMediaSession(context: Context, player: ExoPlayer): MediaSession {
        return MediaSession.Builder(context, player)
            .build()
    }

    @FeatureScope
    @Provides
    fun provideSessionToken(context: Context): SessionToken =
        SessionToken(context, ComponentName(context, PlayerService::class.java))

    @FeatureScope
    @Provides
    fun provideMediaControllerFuture(
        context: Context,
        sessionToken: SessionToken
    ): ListenableFuture<MediaController> {
        return MediaController.Builder(context, sessionToken)
            .buildAsync()
    }
}
