package com.example.activitystates

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.activitystates.databinding.ActivityMainBinding

class SimpleState4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<SimpleState4ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { viewModel.increment() }
        binding.randomColorButton.setOnClickListener { viewModel.setColor() }
        binding.switchVisibleButton.setOnClickListener { viewModel.switchVisibility() }

        viewModel.initState(
            savedInstanceState?.getParcelable(KEY_STATE) ?: SimpleState4ViewModel.State(
                counter = 0,
                color = ContextCompat.getColor(this, R.color.teal_700),
                isVisible = true
            )
        )

        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: SimpleState4ViewModel.State) = with(binding) {
        numberTextView.text = state.counter.toString()
        numberTextView.setTextColor(state.color)
        numberTextView.visibility = if (state.isVisible) VISIBLE else INVISIBLE

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, viewModel.state.value)
    }

    companion object {
        const val KEY_STATE = "STATE"
    }
}