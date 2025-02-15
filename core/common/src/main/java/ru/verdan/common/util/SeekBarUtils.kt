package ru.verdan.common.util

import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener

fun SeekBar.setOnSeekBarChangeListener(
    onProgressChanged: (Int) -> Unit = { },
    onStartTrackingTouch: (Int) -> Unit = { },
    onStopTrackingTouch: (Int) -> Unit = { }
) {
    setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            p0?.apply { onProgressChanged(progress) }
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
            p0?.apply { onStartTrackingTouch(progress) }
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            p0?.apply { onStopTrackingTouch(progress) }
        }
    })
}
