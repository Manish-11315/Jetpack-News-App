package com.mysecondapp.newsapp.presentation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mysecondapp.newsapp.data.model.Article
import com.mysecondapp.newsapp.data.model.Source
import com.mysecondapp.newsapp.presentation.newsviewmodel
import com.mysecondapp.newsapp.presentation.screens.HomeScreen
import com.mysecondapp.newsapp.presentation.screens.SearchScreen
import com.mysecondapp.newsapp.presentation.screens.description_scrren

@Composable
fun appnavigate(viewModel: newsviewmodel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Homescreen) {
        composable<Homescreen> {
            HomeScreen(navController, viewModel)
        }

        composable<Descriptionscreen> {
            val Descriptionscreen = it.toRoute<Descriptionscreen>()
            val article = Article(
                author = it.arguments?.getString("author"),
                content = it.arguments?.getString("content"),
                description = it.arguments?.getString("description"),
                publishedAt = it.arguments?.getString("publishedAt"),
                url = it.arguments?.getString("url"),
                urlToImage = it.arguments?.getString("urlToImage"),
                title = it.arguments?.getString("title"),
                source = Source(
                    id = it.arguments?.getString("id"),
                    name = it.arguments?.getString("name")
                )
            )
            description_scrren(article = article, navController = navController)
        }

        composable<Searchscreen> {
            SearchScreen(navController, viewModel)
        }

    }
}