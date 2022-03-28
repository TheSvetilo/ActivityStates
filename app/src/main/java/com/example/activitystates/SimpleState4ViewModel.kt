package com.example.activitystates

import android.graphics.Color
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

class SimpleState4ViewModel: ViewModel() {

    val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    fun initState(state: State) {
        stateLiveData.value = state
    }

    fun increment() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counter = oldState.counter + 1
        )
    }

    fun setColor() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            color = Color.rgb(
                Random.nextInt(255),
                Random.nextInt(255),
                Random.nextInt(255)
            )
        )
    }

    fun switchVisibility() {
        val oldState = stateLiveData.value
        oldState?.copy(
            isVisible = !oldState.isVisible
        ).also { stateLiveData.value = it }
    }

    @Parcelize
    data class State(
        val counter: Int,
        val color: Int,
        val isVisible: Boolean
    ) : Parcelable
}