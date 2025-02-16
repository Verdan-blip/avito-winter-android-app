package ru.verdan.feature.trackplayer.presentation.service.util

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Metadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

fun Player.isPlayingAsFlow(): Flow<Boolean> = callbackFlow {
    trySendBlocking(isPlaying)
    val listener = object : Player.Listener {

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            trySendBlocking(isPlaying)
        }
    }
    addListener(listener)
    awaitClose { removeListener(listener) }
}

fun Player.currentMediaItemAsFlow(): Flow<MediaItem?> = callbackFlow {
    trySendBlocking(currentMediaItem)
    val listener = object : Player.Listener {

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            trySendBlocking(currentMediaItem)
        }
    }
    addListener(listener)
    awaitClose { removeListener(listener) }
}

fun Player.currentPlayingItemDurationAsFlow(): Flow<Long?> = callbackFlow {
    trySendBlocking(duration)
    val listener = object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == ExoPlayer.STATE_READY) {
                trySendBlocking(duration)
            }
        }
    }

    addListener(listener)
    awaitClose { removeListener(listener) }
}

@OptIn(UnstableApi::class)
fun Player.hasNextItemAsFlow(): Flow<Boolean> = callbackFlow {
    trySendBlocking(false)
    val listener = object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            trySendBlocking(hasNextMediaItem())
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            trySendBlocking(hasNextMediaItem())
        }

        override fun onMetadata(metadata: Metadata) {
            trySendBlocking(hasNextMediaItem())
        }
    }

    addListener(listener)
    awaitClose { removeListener(listener) }
}

@OptIn(UnstableApi::class)
fun Player.hasPreviousItemAsFlow(): Flow<Boolean> = callbackFlow {
    trySendBlocking(false)
    val listener = object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            trySendBlocking(hasPreviousMediaItem())
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            trySendBlocking(hasPreviousMediaItem())
        }

        override fun onMetadata(metadata: Metadata) {
            trySendBlocking(hasPreviousMediaItem())
        }
    }

    addListener(listener)
    awaitClose { removeListener(listener) }
}

fun Player.currentPlayingPositionAsFlow(): Flow<Long> = flow {
    while (true) {
        emit(currentPosition)
        delay(500)
    }
}

fun Player.errorsAsFlow(): Flow<Exception> = callbackFlow {
    val listener = object : Player.Listener {

        override fun onPlayerError(error: PlaybackException) {
            trySendBlocking(error)
        }
    }

    addListener(listener)
    awaitClose { removeListener(listener) }
}
