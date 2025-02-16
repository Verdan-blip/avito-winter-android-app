package ru.verdan.core.theme.view.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import ru.verdan.core.theme.R
import ru.verdan.core.theme.databinding.TrackItemBinding
import ru.verdan.core.theme.entity.TrackModel

class TrackViewHolder(
    private val viewBinding: TrackItemBinding,
    private val onTrackClick: (TrackModel) -> Unit
) : ViewHolder(viewBinding.root) {

    private var currentTrackModel: TrackModel? = null

    init {
        viewBinding.apply {
            root.setOnClickListener { currentTrackModel?.also(onTrackClick) }
        }
    }

    fun bind(track: TrackModel) {
        track.apply {
            viewBinding.apply {
                tvTitle.text = title
                tvArtist.text = artist
                ivCover.load(coverUrl) {
                    crossfade(true)
                    error(R.drawable.image_placeholder)
                }
            }
        }
        currentTrackModel = track
    }
}
