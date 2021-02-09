package com.example.spannablegetspanstartandendbugdemo

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var spanHelper: SpanHelper
    lateinit var mStrikeThroughButton: ImageButton
    lateinit var mItalicChangeButton: ImageButton
    var strikeStatePressed: Boolean = false
    var italicStatePressed: Boolean = false

    val strikeThroughButtonListener = View.OnClickListener { view ->

        if(strikeStatePressed) {
            spanHelper.removeSpan(StrikethroughSpan())
            strikeStatePressed = false
        } else {
            spanHelper.addSpan(StrikethroughSpan())
            strikeStatePressed = true;
        }
    }

    val italicButtonListener = View.OnClickListener { view ->

        if(italicStatePressed) {
            spanHelper.removeSpan(StyleSpan(Typeface.ITALIC))
            italicStatePressed = false
        } else {
            spanHelper.addSpan(StyleSpan(Typeface.ITALIC))
            italicStatePressed = true;
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.text_input)
        mStrikeThroughButton = findViewById(R.id.strikethrough)
        mItalicChangeButton = findViewById(R.id.italictext)
        mStrikeThroughButton.setOnClickListener(strikeThroughButtonListener)
        mItalicChangeButton.setOnClickListener(italicButtonListener)
        spanHelper = SpanHelper(editText)
    }
}