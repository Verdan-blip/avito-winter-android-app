package ru.verdan.feature.loaded.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.verdan.common.di.module.IODispatcher
import ru.verdan.feature.loaded.domain.entity.LoadedTrack
import ru.verdan.feature.loaded.domain.mapper.toLoadedTrackList
import ru.verdan.local.api.repository.TrackLocalRepository
import javax.inject.Inject

class GetLoadedTracksUseCase @Inject constructor(
    private val trackLocalRepository: TrackLocalRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): List<LoadedTrack> {
        return withContext(dispatcher) {
            trackLocalRepository.getAll().toLoadedTrackList()
        }
    }
}
