package com.example.simplerest.ui

import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplerest.ui.screen.HomeScreen
import com.example.simplerest.ui.theme.SimpleRestTheme
import com.example.simplerest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Api:
 * https://randomuser.me/documentation#intro
 * */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleRestTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
        contentColor = MaterialTheme.colorScheme.background,
        topBar= {
            Toolbar (scrollBehavior) { viewModel.addUser() }
        }
    ){
        HomeScreen(
            viewModel = viewModel,
            modifier = Modifier.padding(it)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    scrollBehavior: TopAppBarScrollBehavior,
    action: () ->Unit
){
    TopAppBar(
        title = { Text(text = "Random User Api") },
        actions = {
            IconButton(onClick = action) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Menu")
            }
        },
        scrollBehavior = scrollBehavior
    )
}