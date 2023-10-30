package com.example.storyview.story.adapter

import com.irmak.ui.model.StoryDetailDataUiModel


interface StoryHolderClickListener {

    fun click(model: StoryDetailDataUiModel)
    fun nextStory()
    fun previousStory()
    fun updateProgressState(isPause: Boolean)
}