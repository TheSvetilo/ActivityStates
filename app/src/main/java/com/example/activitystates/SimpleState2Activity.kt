package com.example.activitystates

import android.graphics.Color
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.activitystates.databinding.ActivityMainBinding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class SimpleState2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var counterValue by notNull<Int>()
    private var counterColor by notNull<Int>()
    private var counterVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { incrementNumber() }
        binding.randomColorButton.setOnClickListener { randomColor() }
        binding.switchVisibleButton.setOnClickListener { switchVisibility() }

        if (savedInstanceState == null) {
            counterValue = 0
            counterColor = ContextCompat.getColor(this, R.color.purple_700)
            counterVisible = true
        } else {
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            counterColor = savedInstanceState.getInt(KEY_COLOR)
            counterVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, counterColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterVisible)
    }

    private fun switchVisibility() {
        counterVisible = !counterVisible
        renderState()
    }

    private fun randomColor() {
        counterColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun incrementNumber() {
        counterValue++
        renderState()
    }

    private fun renderState() = with(binding) {
        numberTextView.text = counterValue.toString()
        numberTextView.setTextColor(counterColor)
        numberTextView.visibility = if (counterVisible) VISIBLE else INVISIBLE
    }

    companion object {
        private const val KEY_COUNTER = "COUNTER"
        private const val KEY_COLOR = "COLOR"
        private const val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}