package com.mysecondapp.newsapp.presentation.screens

import android.graphics.fonts.Font
import android.icu.number.Scale
import android.window.BackEvent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.mysecondapp.newsapp.util.dimens
import com.mysecondapp.newsapp.R
import com.mysecondapp.newsapp.data.model.Article
import com.mysecondapp.newsapp.presentation.newsviewmodel
import com.mysecondapp.newsapp.presentation.routes.Searchscreen
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun description_scrren(article: Article,navController: NavController) {
    val is_system_in_dark = isSystemInDarkTheme()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (is_system_in_dark == true) colorResource(id = R.color.dark_blue) else colorResource(
                        id = R.color.Light_primary
                    ),
                    titleContentColor = if (is_system_in_dark == true) colorResource(id = R.color.Light_primary) else colorResource(
                        id = R.color.dark_blue
                    )

                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier.size(56.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp),
                                tint = if (is_system_in_dark == true) colorResource(id = R.color.Light_primary) else colorResource(
                                    id = R.color.dark_blue
                                )
                            )
                        }
                        Spacer(
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "News App",
                            color = if (is_system_in_dark == true) colorResource(id = R.color.Light_primary) else colorResource(
                                id = R.color.dark_blue
                            )
                        )
                    }

                }

            )
        }) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(13.dp),
                elevation = CardDefaults.cardElevation(20.dp)

            ) {
                if(article.urlToImage.isNullOrEmpty().not()){
                    AsyncImage(
                        article.urlToImage,
                        contentDescription = "image",
                        modifier = Modifier.height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Image(
                        painter = painterResource(R.drawable.news_app_default),
                        contentDescription = null,
                        modifier = Modifier.height(200.dp),
                        contentScale = ContentScale.Crop,
                    )
                }

            }
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(13.dp),
                elevation = CardDefaults.cardElevation(20.dp),
                colors = CardDefaults.cardColors(
                    if (is_system_in_dark){
                        colorResource(id = R.color.dark_blue)
                    }else{
                        colorResource(id = R.color.Light_primary)
                    }
                )
            ) {
                Text(
                    text = article.title.toString(),
                    color = if (is_system_in_dark){
                        colorResource(id = R.color.Light_primary)
                    }else{
                        colorResource(id = R.color.dark_blue)
                    },
                    modifier = Modifier.padding(20.dp),
                    fontWeight = FontWeight.ExtraBold,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp
                    )
                )

            }
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(13.dp),
                elevation = CardDefaults.cardElevation(20.dp),
                colors = CardDefaults.cardColors(
                    if (is_system_in_dark){
                        colorResource(id = R.color.dark_blue)
                    }else{
                        colorResource(id = R.color.Light_primary)
                    }
                )
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Description",
                        fontSize = 25.sp
                    )
                }
                Text(
                    text = article.description.toString(),
                    color = if (is_system_in_dark){
                        colorResource(id = R.color.Light_primary)
                    }else{
                        colorResource(id = R.color.dark_blue)
                    },
                    modifier = Modifier.padding(20.dp),
                    fontWeight = FontWeight.ExtraBold,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 15.sp
                    )
                )

            }
        }
    }

}