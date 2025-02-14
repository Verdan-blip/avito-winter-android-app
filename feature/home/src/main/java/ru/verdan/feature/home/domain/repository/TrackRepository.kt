package ru.verdan.feature.home.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.verdan.feature.home.domain.entity.Track

interface TrackRepository {

    suspend fun getChartTracks(): List<Track>

    suspend fun searchByName(name: String): Flow<PagingData<Track>>
}
