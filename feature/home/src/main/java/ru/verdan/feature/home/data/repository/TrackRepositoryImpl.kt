package ru.verdan.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.verdan.feature.home.data.datasource.TrackChartsDataSource
import ru.verdan.feature.home.data.datasource.TrackSearchPagingSource
import ru.verdan.feature.home.data.mapper.toTrack
import ru.verdan.feature.home.data.mapper.toTrackList
import ru.verdan.feature.home.domain.entity.Track
import ru.verdan.feature.home.domain.repository.TrackRepository
import ru.verdan.network.api.config.DeezerApiConfig
import javax.inject.Inject

internal class TrackRepositoryImpl @Inject constructor(
    private val trackChartDataSource: TrackChartsDataSource,
    private val trackSearchPagingSourceFactory: TrackSearchPagingSource.Companion.Factory
) : TrackRepository {

    override fun getChartTracks(): List<Track> {
        return trackChartDataSource.getChartTracks()
            .toTrackList()
    }

    override fun searchByName(name: String): Flow<PagingData<Track>> {
        return Pager(
            config = PagingConfig(pageSize = DeezerApiConfig.PAGING_STEP),
            pagingSourceFactory = {
                trackSearchPagingSourceFactory.create(name)
            }
        ).flow
            .flowOn(Dispatchers.IO)
            .map { pagingData ->
                pagingData.map { trackEntity -> trackEntity.toTrack() }
            }
    }
}
