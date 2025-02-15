package ru.verdan.feature.trackplayer.presentation.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri

class DownloadCompletionReceiver(
    private val action: (downloadId: Long, newUri: Uri) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.apply {
            context?.also { ctx ->
                val downloadManager = ctx.getSystemService(DownloadManager::class.java)
                val id = getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id != -1L) {
                    downloadManager?.getUriForDownloadedFile(id)?.also { newUri ->
                        action(id, newUri)
                    }
                }
            }
        }
    }
}
