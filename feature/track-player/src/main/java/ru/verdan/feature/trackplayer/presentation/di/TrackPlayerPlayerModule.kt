package ru.verdan.feature.trackplayer.presentation.di

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import ru.verdan.common.di.scopes.FeatureScope

@Module
internal class TrackPlayerPlayerModule {

    @OptIn(UnstableApi::class)
    @FeatureScope
    @Provides
    fun provideExoPlayer(
        context: Context
    ): ExoPlayer {
        return ExoPlayer.Builder(context)
            .build()
    }
}
