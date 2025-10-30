package com.mysecondapp.newsapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.mysecondapp.newsapp.R
import com.mysecondapp.newsapp.presentation.newsviewmodel
import com.mysecondapp.newsapp.presentation.routes.Descriptionscreen
import com.mysecondapp.newsapp.presentation.routes.Searchscreen
import com.mysecondapp.newsapp.util.dimens


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController, viewModel: newsviewmodel) {
    val state = viewModel.currentstate.collectAsState()
    val search_item =
        arrayOf("Cricket", "India", "Technology", "Android", "US", "Fashion", "Finance")
    val is_system_in_dark = isSystemInDarkTheme()
    val selected_category = rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (is_system_in_dark == true) colorResource(id = R.color.dark_blue) else colorResource(
                        id = R.color.Light_primary
                    ),
                    titleContentColor = if (is_system_in_dark == true) colorResource(id = R.color.Light_primary) else colorResource(
                        id = R.color.dark_blue
                    ),

                    ),
                title = {
                    Text("News App")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Searchscreen)

                        },
                        modifier = Modifier.size(56.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search",
                            modifier = Modifier.size(24.dp),
                            tint = if (is_system_in_dark == true) colorResource(id = R.color.Light_primary) else colorResource(
                                id = R.color.dark_blue
                            )
                        )
                    }
                }

            )
        }
    ) { innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {

            if (state.value.is_loading == true) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.value.is_error.isNullOrEmpty().not()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {
                    Text(text = state.value.is_error.toString())
                }
            } else {
                val data = state.value.data_loaded
                if (data?.articles!!.isEmpty()) {
                    Text(text = "No news found")
                } else {

                    Column {

                        LazyRow(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .background(
                                    if (is_system_in_dark == true) colorResource(id = R.color.dark_blue) else colorResource(
                                        id = R.color.Light_primary
                                    )
                                )

                        ) {
                            items(search_item) {
                                it.let {
                                    ElevatedCard(
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (it.equals(selected_category.value)) {
                                                MaterialTheme.colorScheme.primary

                                            } else {
                                                MaterialTheme.colorScheme.background
                                            }
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        onClick = {
                                            viewModel.showeverythingnews(it)
                                            selected_category.value = it
                                        },
                                        shape = RoundedCornerShape(corner = CornerSize(25.dp))

                                    ) {
                                        Text(
                                            text = it,
                                            fontSize = 18.sp,
                                            modifier = Modifier.padding(
                                                start = 40.dp,
                                                end = 40.dp,
                                                top = 8.dp,
                                                bottom = 8.dp
                                            )

                                        )
                                    }
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier.padding(1.dp)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            val article = data.articles
                            items(article) { it ->
                                if (it.title?.contains("Removed") != true) {

                                    ElevatedCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .padding(dimens.padding_news_card),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                                        onClick = {
                                            navController.navigate(
                                                Descriptionscreen(
                                                    author = it.author,
                                                    content = it.content,
                                                    description = it.description,
                                                    publishedAt = it.publishedAt,
                                                    id = it.source.id,
                                                    name = it.source.name,
                                                    title = it.title,
                                                    url = it.url,
                                                    urlToImage = it.urlToImage
                                                )
                                            )
                                        }
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                        ) {
                                            ElevatedCard(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(12.dp)
                                            ) {
                                                if (it.urlToImage.isNullOrEmpty().not()) {
                                                    AsyncImage(
                                                        model = it.urlToImage,
                                                        contentDescription = null,
                                                        placeholder = painterResource(R.drawable.news_app_default),
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                } else {
                                                    Image(
                                                        painter = painterResource(R.drawable.news_app_default),
                                                        contentDescription = null,
                                                        modifier = Modifier.fillMaxSize(),
                                                        contentScale = ContentScale.Crop
                                                    )
                                                }
                                            }
                                            it.title?.let {
                                                Text(text = it, Modifier.padding(10.dp))
                                            }
                                        }
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }
    }
}





