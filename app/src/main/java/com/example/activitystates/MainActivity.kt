package com.example.activitystates

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.activitystates.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener {
            incrementNumber()
        }

        binding.randomColorButton.setOnClickListener {
            setRandomColor()
        }

        binding.switchVisibleButton.setOnClickListener {
            switchVisibility()
        }
    }

    private fun switchVisibility() {
        if (binding.numberTextView.visibility == VISIBLE) {
            binding.numberTextView.visibility = INVISIBLE
        } else {
            binding.numberTextView.visibility = VISIBLE
        }
    }

    private fun setRandomColor() {
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.numberTextView.setTextColor(randomColor)
    }

    private fun incrementNumber() {
        var counter = binding.numberTextView.text.toString().toInt()
        counter++
        binding.numberTextView.setText(counter.toString())
    }
}