package com.mysecondapp.newsapp.presentation.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mysecondapp.newsapp.presentation.newsviewmodel
import com.mysecondapp.newsapp.presentation.routes.Homescreen
import com.mysecondapp.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchScreen(navController: NavController, viewModel: newsviewmodel) {
    val search_term = rememberSaveable() { mutableStateOf("") }

    NewsAppTheme() {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerpadding ->
            Column(
                modifier = Modifier.padding(innerpadding)
            ) {
                Text(text = "Enter The topic of News", modifier = Modifier.padding(20.dp))
                OutlinedTextField(
                    value = search_term.value,
                    onValueChange = {
                        search_term.value = it
                    },
                    singleLine = true,
                    label = {
                        Text(text = "Search news")
                    },
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (search_term.value.isNotEmpty()){
                                    viewModel.showeverythingnews(search_term.value)
                                    navController.navigate(Homescreen){
                                        popUpTo(Homescreen) { inclusive = true }
                                        launchSingleTop = true

                                    }
                                }
                            }
                        ) {
                            Icon (
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    },

                )
            }
        }
    }

}