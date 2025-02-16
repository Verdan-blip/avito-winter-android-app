package ru.verdan.local.impl.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import ru.verdan.common.di.scopes.ApplicationScope
import ru.verdan.local.api.entity.TrackEntity
import ru.verdan.local.api.repository.TrackLocalRepository
import ru.verdan.local.impl.datasource.TrackDataSource
import javax.inject.Inject

@ApplicationScope
internal class TrackLocalRepositoryImpl @Inject constructor(
    private val trackDataSource: TrackDataSource,
    context: Context
) : TrackLocalRepository {

    private val downloadManager: DownloadManager = context
        .getSystemService(DownloadManager::class.java)

    override suspend fun downloadTrackFile(trackEntity: TrackEntity): Long {
        val fileName = FILENAME_FORMAT.format(trackEntity.id)
        val request = DownloadManager.Request(trackEntity.audioUrl.toUri())
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                    or DownloadManager.Request.NETWORK_MOBILE
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        return downloadManager.enqueue(request)
    }

    override suspend fun saveTrack(trackEntity: TrackEntity) {
        trackDataSource.insert(trackEntity)
    }

    override suspend fun getAll(): List<TrackEntity> {
        return trackDataSource.getAll()
    }

    override suspend fun getById(id: Long): TrackEntity? {
        return trackDataSource.getById(id)
    }

    override suspend fun getAllByName(title: String): List<TrackEntity> {
        return trackDataSource.getAllByName(title)
    }

    companion object {

        const val FILENAME_FORMAT = "track%s"
    }
}
