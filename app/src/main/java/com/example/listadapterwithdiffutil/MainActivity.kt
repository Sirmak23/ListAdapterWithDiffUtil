package com.example.listadapterwithdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listadapterwithdiffutil.databinding.ActivityMainBinding
import com.example.listadapterwithdiffutil.ui.model.StoryData
import com.example.listadapterwithdiffutil.ui.model.StoryModel
import com.example.listadapterwithdiffutil.ui.story.StoryAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var storyAdapter: StoryAdapter
    private var storyList = StoryData.getStoryData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBinding()
    }

    private fun initBinding(){
        with(binding){
            recyclerview.apply {
                adapter = StoryAdapter {item->
                    getStoryList(item)
                }.apply {
                    storyAdapter = this
                }
                storyAdapter.submitList(storyList)
            }
        }
    }

    private fun getStoryList(selectedItem: StoryModel){
        storyAdapter.submitList(storyAdapter.currentList.map { newItem ->
            if (newItem == selectedItem) {
                newItem.copy(isClicked = true)
            } else {
                newItem
            }
        })

    }
}