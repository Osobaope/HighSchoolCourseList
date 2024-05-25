package com.example.highschoolcourselist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicNameId: Int,
    val associatedTopicAmount: Int,
    @DrawableRes val topicImageId: Int
)
