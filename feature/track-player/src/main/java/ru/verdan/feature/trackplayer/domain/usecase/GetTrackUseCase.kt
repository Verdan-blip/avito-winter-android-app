package ru.verdan.feature.trackplayer.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.verdan.common.di.module.IODispatcher
import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.domain.mapper.toTrack
import ru.verdan.feature.trackplayer.domain.repository.TrackRepository
import ru.verdan.local.api.repository.TrackLocalRepository
import javax.inject.Inject

class GetTrackUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    private val trackLocalRepository: TrackLocalRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Long): Track {
        return withContext(dispatcher) {
            trackLocalRepository.getById(id)?.toTrack()
                ?: trackRepository.getTrackById(id)
        }
    }
}
