package com.example.listadapterwithdiffutil.ui.story

import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterwithdiffutil.ui.model.StoryModel
import com.example.listadapterwithdiffutil.databinding.StoryLayoutBinding
import com.example.listadapterwithdiffutil.ui.extension.loadFromUrl

class StoryHolder(
    private val binding: StoryLayoutBinding,
    private val onItemClick:(Int)-> Unit
):RecyclerView.ViewHolder(binding.root) {

    fun bind(model: StoryModel){
        with(binding){
            title.text = model.title
            image.loadFromUrl(model.imageSource)
            image.alpha = if (model.isClicked) 0.5f else 1f
            title.alpha = if (model.isClicked) 0.5f else 1f
            imageWithStroke.alpha = if (model.isClicked) 0.5f else 1f
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }
}