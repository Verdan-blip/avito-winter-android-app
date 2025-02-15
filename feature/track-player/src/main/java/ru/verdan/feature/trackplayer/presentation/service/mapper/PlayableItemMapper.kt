package ru.verdan.feature.trackplayer.presentation.service.mapper

import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import ru.verdan.feature.trackplayer.presentation.entity.TrackModel

fun TrackModel.toMediaItem(): MediaItem {
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

fun MediaItem.toTrackModel(): TrackModel {
    return TrackModel(
        id = mediaId.toLong(),
        title = mediaMetadata.title.toString(),
        albumTitle = mediaMetadata.albumTitle?.toString(),
        audioUrl = localConfiguration?.uri?.toString()
            ?: throw IllegalStateException("Audio url has not been provided"),
        coverUrl = mediaMetadata.artworkUri?.toString(),
        artist = mediaMetadata.artist?.toString()
            ?: throw IllegalStateException("Artist has not been provided"),
        isSaved = false
    )
}
