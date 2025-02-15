package ru.verdan.feature.trackplayer.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.verdan.common.di.module.IODispatcher
import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.domain.mapper.toTrackEntity
import ru.verdan.local.api.repository.TrackRepository
import javax.inject.Inject

class SaveDownloadedTrackUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(track: Track) {
        withContext(dispatcher) {
            trackRepository.saveTrack(track.toTrackEntity())
        }
    }
}
