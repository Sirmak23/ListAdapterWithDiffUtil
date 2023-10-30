package com.example.listadapterwithdiffutil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listadapterwithdiffutil.databinding.ActivityMainBinding
import com.example.listadapterwithdiffutil.ui.StoryDetailViewFragment
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
                    openStoryDetailViewFragment()
                }.apply {
                    storyAdapter = this
                }
                storyAdapter.submitList(storyList)
            }
        }
    }
    private fun openStoryDetailViewFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = StoryDetailViewFragment()

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment != null) {
            transaction.remove(currentFragment)
        }

        transaction.add(android.R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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