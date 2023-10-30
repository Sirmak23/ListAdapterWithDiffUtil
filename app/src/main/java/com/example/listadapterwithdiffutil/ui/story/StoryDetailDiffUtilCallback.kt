package com.example.storyview.story.adapter

import androidx.recyclerview.widget.DiffUtil
import com.irmak.ui.model.StoryDetailDataUiModel

class StoryDetailDiffUtilCallback : DiffUtil.ItemCallback<StoryDetailDataUiModel>() {
    override fun areItemsTheSame(oldItem: StoryDetailDataUiModel, newItem: StoryDetailDataUiModel): Boolean {
        return oldItem.subTitle == newItem.subTitle
    }

    override fun areContentsTheSame(oldItem: StoryDetailDataUiModel, newItem: StoryDetailDataUiModel): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}