package ru.verdan.core.theme.view.adapter

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.verdan.core.theme.databinding.TrackItemBinding
import ru.verdan.core.theme.entity.TrackModel
import ru.verdan.core.theme.view.diffutil.TrackItemCallback
import ru.verdan.core.theme.view.holder.TrackViewHolder

class TrackPagingAdapter(
    private val context: Context,
    private val onTrackClick: (TrackModel) -> Unit
) : PagingDataAdapter<TrackModel, TrackViewHolder>(TrackItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(
            viewBinding = TrackItemBinding.inflate(
                (context as Activity).layoutInflater, parent, false
            ),
            onTrackClick = onTrackClick
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.also { item -> holder.bind(item) }
    }
}
