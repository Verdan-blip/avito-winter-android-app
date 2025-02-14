package ru.verdan.feature.home.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.verdan.feature.home.domain.entity.Track
import ru.verdan.feature.home.domain.repository.TrackRepository
import javax.inject.Inject

class SearchTrackByNameUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(name: String): Flow<PagingData<Track>> {
        return withContext(dispatcher) {
            trackRepository.searchByName(name)
        }
    }
}
