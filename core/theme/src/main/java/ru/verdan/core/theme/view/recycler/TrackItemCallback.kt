package ru.verdan.core.theme.view.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.verdan.core.theme.entity.TrackModel

object TrackItemCallback : DiffUtil.ItemCallback<TrackModel>() {

    override fun areItemsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
        return oldItem == newItem
    }
}
