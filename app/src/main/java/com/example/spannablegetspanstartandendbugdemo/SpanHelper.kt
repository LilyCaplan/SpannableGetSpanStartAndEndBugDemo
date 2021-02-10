package com.example.spannablegetspanstartandendbugdemo

import android.graphics.Typeface
import android.text.SpanWatcher
import android.text.Spannable
import android.text.style.CharacterStyle
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.util.Log
import android.widget.EditText

class SpanHelper(val editText: EditText) {

    val watcher: SpanWatcher = object : SpanWatcher {
        override fun onSpanAdded(
            text: Spannable, what: Any,
            start: Int, end: Int
        ) {
            logSpanStartAndEnd(what, text)
        }

        override fun onSpanRemoved(
            text: Spannable, what: Any,
            start: Int, end: Int
        ) {
            logSpanStartAndEnd(what, text)
        }

        override fun onSpanChanged(
            text: Spannable, what: Any,
            ostart: Int, oend: Int, nstart: Int, nend: Int
        ) {
            logSpanStartAndEnd(what, text)
        }
    }

    init {
        editText.text.setSpan(watcher, 0, editText.text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }

    /**
     * @param span The span style to add to the editText text
     * */
    fun addSpan(span: CharacterStyle) {
        val currentText = editText.text
        currentText.setSpan(
            span,
            editText.selectionStart,
            editText.selectionEnd,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

    /**
     * @param span The span style to remove from the editText text
     * */
    fun removeSpan(spanType: CharacterStyle) {
        val currentText = editText.text
        val selectionStart: Int = editText.getSelectionStart()
        val selectionEnd: Int = editText.getSelectionEnd()

        if (spanType is StyleSpan) {
            val styleSpans: Array<StyleSpan> = editText.getText().getSpans(
                selectionStart, selectionEnd,
                StyleSpan::class.java
            )

            for (span in styleSpans) {
                currentText.removeSpan(span)
            }
        } else if (spanType is StrikethroughSpan) {
            val strikethroughSpans: Array<StrikethroughSpan> = editText.getText().getSpans(
                selectionStart, selectionEnd,
                StrikethroughSpan::class.java
            )
            for (strikethroughSpan in strikethroughSpans) {
                currentText.removeSpan(strikethroughSpan)
            }
        }
    }

    /**
     * @param listener
     *      The {@link StrikethroughSpan} or {@link StyleSpan} that would have the start and end
     *      point of the spans we need to log
     * @param text
     *      The text in the editText that the user has typed
     * */
    fun logSpanStartAndEnd(listener: Any, text: Spannable) {

        if (listener is StrikethroughSpan) {
            val spanArray = text.getSpans(0, text.length, StrikethroughSpan::class.java)
            for (span in spanArray) {
                Log.v(
                    MainActivity::class.java.name,
                    "StrikeThrough Span Start: " + text.getSpanStart(span) +
                            " StrikeThrough Span End: " + text.getSpanEnd(span)
                )
            }
        } else if (listener is StyleSpan) {
            val spanArray = text.getSpans(0, text.length, StyleSpan::class.java)
            for (span in spanArray) {
                /**
                 * Here you'll notice the issue upon adding a character to the end of a span. The
                 * spans' starts and stops will be the entirety of the text instead of their respective
                 * spans that have been added prior.
                 * */
                Log.v(
                    MainActivity::class.java.name,
                    "StyleSpan Span Start: " + text.getSpanStart(span) +
                            "StyleSpan Span End: " + text.getSpanEnd(span)
                )
            }
        }

    }
}