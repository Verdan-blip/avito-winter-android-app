package ru.verdan.core.theme.view.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.verdan.core.theme.R

class DefaultItemDecoration(
    private val context: Context
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val normalPadding = context.resources
            .getDimensionPixelOffset(R.dimen.padding_normal)
        val mediumPadding = context.resources
            .getDimensionPixelOffset(R.dimen.padding_medium)
        outRect.apply {
            bottom = normalPadding
            right = mediumPadding
            left = mediumPadding
        }
    }
}
