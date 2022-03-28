package com.example.activitystates

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.activitystates.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import kotlin.random.Random

@Parcelize
class State(
    var value: Int,
    var color: Int,
    var isVisible: Boolean
): Parcelable

class SimpleState3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { incrementNumber() }
        binding.randomColorButton.setOnClickListener { randomColor() }
        binding.switchVisibleButton.setOnClickListener { switchVisibility() }

        state = savedInstanceState?.getParcelable(KEY_STATE) ?:
            State(
                value = 0,
                color = ContextCompat.getColor(this, R.color.teal_200),
                isVisible = true
            )
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(KEY_STATE, state)
    }

    private fun switchVisibility() {
        state.isVisible = !state.isVisible
        renderState()
    }

    private fun randomColor() {
        state.color = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun incrementNumber() {
        state.value++
        renderState()
    }

    private fun renderState() = with(binding) {
        numberTextView.text = state.value.toString()
        numberTextView.setTextColor(state.color)
        numberTextView.visibility = if (state.isVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        private const val KEY_STATE = "STATE"
    }
}