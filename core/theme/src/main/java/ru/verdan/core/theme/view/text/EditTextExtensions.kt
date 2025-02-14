package ru.verdan.core.theme.view.text

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChange(body: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            body(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) { }
    })
}
