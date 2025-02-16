package ru.verdan.local.impl.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tracks"
)
class TrackDbEntity(
    @PrimaryKey @ColumnInfo("id") val id: Long,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("album_title") val albumTitle: String?,
    @ColumnInfo("artist") val artist: String,
    @ColumnInfo("cover_url") val coverUrl: String?,
    @ColumnInfo("audio_url") val audioUrl: String
)
