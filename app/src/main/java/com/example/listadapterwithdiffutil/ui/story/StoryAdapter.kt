package com.example.listadapterwithdiffutil.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.listadapterwithdiffutil.ui.model.StoryModel
import com.example.listadapterwithdiffutil.databinding.StoryLayoutBinding

class StoryAdapter(
    private val onItemClicked : (StoryModel) -> Unit
):ListAdapter<StoryModel, StoryHolder>(StoryCallback()){


override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder {
    val binding =
        StoryLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    return StoryHolder(binding) {
        onItemClicked(getItem(it))
    }
}

override fun onBindViewHolder(holder: StoryHolder, position: Int) {
    holder.bind(
        model = getItem(position)
    )
}
}
