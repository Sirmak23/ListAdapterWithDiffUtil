package com.example.storyview.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irmak.ui.model.StoryDetailDataUiModel

class StoryDetailViewModel : ViewModel() {
    private val _storyData = MutableLiveData<List<StoryDetailDataUiModel>>()
    val storyData: LiveData<List<StoryDetailDataUiModel>>
        get() = _storyData

    init {
        loadStoryData()
    }

    private fun loadStoryData() {
        val data = listOf(
            StoryDetailDataUiModel(
                title = "Story-1",
                subTitle = "Text-1",
                imageUrl = "https://img.freepik.com/premium-photo/cyberpunk-gaming-controller-gamepad-joystick-illustration_691560-5812.jpg"
            ),
            StoryDetailDataUiModel(
                title = "Story-2",
                subTitle = "Text-2",
                imageUrl = "https://dfstudio-d420.kxcdn.com/wordpress/wp-content/uploads/2019/06/digital_camera_photo-1080x675.jpg"
            ),
            StoryDetailDataUiModel(
                title = "Story-3",
                subTitle = "Text-3",
                imageUrl = "https://img.freepik.com/premium-photo/cyberpunk-gaming-controller-gamepad-joystick-illustration_691560-5812.jpg"
            ),
            StoryDetailDataUiModel(
                title = "Story-4",
                subTitle = "Text-4",
                imageUrl = "https://dfstudio-d420.kxcdn.com/wordpress/wp-content/uploads/2019/06/digital_camera_photo-1080x675.jpg"
            )
        )
        _storyData.value = data
    }
}
