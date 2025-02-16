package ru.verdan.feature.trackplayer.presentation.di

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import dagger.Module
import dagger.Provides
import ru.verdan.common.di.scopes.FeatureScope

@Module
internal class TrackPlayerPlayerModule {

    @OptIn(UnstableApi::class)
    @Provides
    fun provideDataSourceFactory(
        context: Context,
    ): DataSource.Factory {
        return DefaultDataSource.Factory(context)
    }

    @OptIn(UnstableApi::class)
    @Provides
    fun provideMediaSourceFactory(
        context: Context,
        dataSourceFactory: DataSource.Factory
    ): MediaSource.Factory {
        return DefaultMediaSourceFactory(dataSourceFactory)
    }

    @OptIn(UnstableApi::class)
    @FeatureScope
    @Provides
    fun provideExoPlayer(
        context: Context,
        mediaSourceFactory: MediaSource.Factory
    ): ExoPlayer {
        return ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()
    }
}
