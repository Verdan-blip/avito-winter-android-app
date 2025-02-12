package ru.verdan.core.theme.view.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import ru.verdan.core.theme.R
import ru.verdan.core.theme.databinding.TrackItemBinding
import ru.verdan.core.theme.entity.TrackModel

class TrackViewHolder(
    private val viewBinding: TrackItemBinding
) : ViewHolder(viewBinding.root) {

    fun bind(track: TrackModel) {
        track.apply {
            viewBinding.apply {
                tvTitle.text = title
                tvArtist.text = artists
                ivCover.load(coverUrl) {
                    crossfade(true)
                    error(R.drawable.image_placeholder)
                }
            }
        }
    }
}
