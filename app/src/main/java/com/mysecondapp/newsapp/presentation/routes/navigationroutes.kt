package com.mysecondapp.newsapp.presentation.routes

import com.mysecondapp.newsapp.data.model.Source
import kotlinx.serialization.Serializable

@Serializable
object Homescreen

@Serializable
data class Descriptionscreen(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val id: String?,
    val name: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

@Serializable
object Searchscreen