package ru.verdan.player.impl.mapper

import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import ru.verdan.player.entity.PlayableItem

fun PlayableItem.toMediaItem(): MediaItem {
    val mediaMetadata = MediaMetadata.Builder()
        .setTitle(title)
        .setArtworkUri(coverUrl?.toUri())
        .setAlbumTitle(albumTitle)
        .setArtist(artist)
        .build()

    return MediaItem.Builder()
        .setMediaMetadata(mediaMetadata)
        .setMediaId(id.toString())
        .setUri(audioUrl.toUri())
        .build()
}

fun MediaItem.toMusicItem(): PlayableItem {
    return PlayableItem(
        id = mediaId.toLong(),
        title = mediaMetadata.title.toString(),
        albumTitle = mediaMetadata.albumTitle?.toString(),
        audioUrl = localConfiguration?.uri?.toString()
            ?: throw IllegalStateException("Audio url has not been provided"),
        coverUrl = mediaMetadata.artworkUri?.toString(),
        artist = mediaMetadata.artist?.toString()
            ?: throw IllegalStateException("Artist has not been provided"),
    )
}
