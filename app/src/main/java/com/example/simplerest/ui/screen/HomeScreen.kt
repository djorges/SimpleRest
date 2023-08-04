package com.example.simplerest.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplerest.R
import com.example.simplerest.domain.User
import com.example.simplerest.ui.state.UiState
import com.example.simplerest.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen (
    viewModel: MainViewModel,
    modifier: Modifier
){
    val state by viewModel.state.collectAsState()
    val users by viewModel.users.collectAsState(emptyList())

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
        ) {
            items(
                items = users
            ) { user ->
                UserItem(user)
            }
        }

        when (state) {
            is UiState.Error -> {
                Text(
                    text = (state as UiState.Error).exception.toString(),
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            is UiState.Loading -> {
                if ((state as UiState.Loading).isLoading) {
                    CircularProgressIndicator(modifier = modifier)
                }
            }
        }
    }
}

@Preview
@Composable
fun UserItem(user: User = User(1,"Pablo","Ferreira","Montevideo","https://randomuser.me/api/portraits/thumb/men/75.jpg")){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("userCard")
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier.weight(1f),
                contentDescription = "user image"
            )
            Spacer(modifier = Modifier.width(8.dp))

        }
    }
}