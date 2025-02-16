package ru.verdan.feature.home.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.verdan.feature.home.data.api.TrackApiService
import ru.verdan.feature.home.data.entity.TrackEntity
import ru.verdan.feature.home.data.mapper.toTrackEntityList
import ru.verdan.network.api.config.DeezerApiConfig

internal class TrackSearchPagingSource @AssistedInject constructor(
    private val trackApiService: TrackApiService,
    @Assisted private val name: String
) : PagingSource<Int, TrackEntity>() {

    override fun getRefreshKey(state: PagingState<Int, TrackEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackEntity> {
        return try {
            val index = params.key ?: 0
            val searchResponse = trackApiService.searchTrackByName(name, index)
            val limit = searchResponse.total
            val step = DeezerApiConfig.PAGING_STEP
            LoadResult.Page(
                data = searchResponse
                    .data
                    .toTrackEntityList(),
                prevKey = if (index == 0) null else index - 1,
                nextKey = if (index < limit) index + step else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {

        @AssistedFactory
        interface Factory {

            fun create(name: String): TrackSearchPagingSource
        }
    }
}
