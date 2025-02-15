package ru.verdan.feature.trackplayer.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.verdan.common.di.module.IODispatcher
import ru.verdan.feature.trackplayer.domain.entity.Track
import ru.verdan.feature.trackplayer.domain.repository.TrackRepository
import javax.inject.Inject

class GetTrackUseCase @Inject constructor(
    private val getTrackRepository: TrackRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Long): Track {
        return withContext(dispatcher) {
            getTrackRepository.getTrackById(id)
        }
    }
}
