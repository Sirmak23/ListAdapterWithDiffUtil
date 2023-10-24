package com.example.listadapterwithdiffutil.ui.story

import androidx.recyclerview.widget.DiffUtil
import com.example.listadapterwithdiffutil.ui.model.StoryModel

class StoryCallback:DiffUtil.ItemCallback<StoryModel>() {
    override fun areItemsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}