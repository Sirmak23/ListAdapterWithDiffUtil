package com.example.storyview.story.adapter

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterwithdiffutil.databinding.StoryDetailLayoutBinding
import com.example.listadapterwithdiffutil.ui.extension.loadFromUrl
import com.irmak.ui.model.StoryDetailDataUiModel

class StoryDetailHolder(
    private val binding: StoryDetailLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: StoryDetailDataUiModel, listener: StoryHolderClickListener) {
        with(binding) {
            storyDetailImageView.loadFromUrl(
                model.imageUrl)//fixme
            storyDetailTitle.text = model.title
            storyDetailSubTitle.text = model.subTitle
            storyDetailButton.setOnClickListener {
                listener.click(model)
            }
            previousStory(listener)
            nextStory(listener)
        }
    }

    private fun nextStory(listener: StoryHolderClickListener) {
        var endTime: Long
        var startTime = 0L

        binding.secondScreenRight.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    listener.updateProgressState(true)
                    startTime = System.currentTimeMillis()
                    return@setOnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    listener.updateProgressState(false)
                    endTime = System.currentTimeMillis()
                    val clickDuration = endTime - startTime
                    if (clickDuration < 200) {
                        listener.nextStory()
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun previousStory(listener: StoryHolderClickListener) {
        var endTime: Long
        var startTime = 0L

        binding.secondScreenLeft.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    listener.updateProgressState(true)
                    startTime = System.currentTimeMillis()
                    return@setOnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    listener.updateProgressState(false)
                    endTime = System.currentTimeMillis()
                    val clickDuration = endTime - startTime
                    if (clickDuration < 200) {
                        listener.previousStory()
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }
    }


}
