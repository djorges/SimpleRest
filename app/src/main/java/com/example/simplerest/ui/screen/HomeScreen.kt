package com.example.simplerest.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplerest.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen (
    viewModel: MainViewModel
){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ){
        Text(
            text = "Title"
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
        Button(
            onClick = { viewModel.getUser()}
        ) {
            Text(text = "Click Me")
        }
    }
}

