package com.example.listadapterwithdiffutil.ui.model

data class StoryModel(
    val id:Int,
    val title:String,
    val imageSource:String,
    val isClicked:Boolean = false
)
