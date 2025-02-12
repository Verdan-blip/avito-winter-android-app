package ru.verdan.core.theme.view.recycler

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.verdan.core.theme.databinding.TrackItemBinding
import ru.verdan.core.theme.entity.TrackModel

class TrackAdapter(
    private val context: Context
) : ListAdapter<TrackModel, TrackViewHolder>(TrackItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(
            TrackItemBinding.inflate((context as Activity).layoutInflater)
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
