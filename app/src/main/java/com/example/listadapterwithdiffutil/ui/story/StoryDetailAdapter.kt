package com.example.storyview.story.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.listadapterwithdiffutil.databinding.StoryDetailLayoutBinding
import com.irmak.ui.model.StoryDetailDataUiModel


class StoryDetailAdapter(
    private val listener: StoryHolderClickListener
) : ListAdapter<StoryDetailDataUiModel, StoryDetailHolder>(StoryDetailDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryDetailHolder {
        val binding = StoryDetailLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoryDetailHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryDetailHolder, position: Int) {
        holder.bind(
            model = getItem(position),
            listener = listener
        )
    }
}
