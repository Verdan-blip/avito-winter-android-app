package ru.verdan.feature.home.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.verdan.common.di.module.IODispatcher
import ru.verdan.feature.home.domain.entity.Track
import ru.verdan.feature.home.domain.repository.TrackRepository
import javax.inject.Inject

class GetChartTracksUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): List<Track> {
        return withContext(dispatcher) {
            trackRepository.getChartTracks()
        }
    }
}
