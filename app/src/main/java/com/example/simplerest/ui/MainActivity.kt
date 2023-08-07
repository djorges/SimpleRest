package com.example.simplerest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.simplerest.R
import com.example.simplerest.ui.screen.HomeScreen
import com.example.simplerest.ui.screen.NavDrawerItem
import com.example.simplerest.ui.screen.NavDrawerOptions
import com.example.simplerest.ui.screen.SettingsScreen
import com.example.simplerest.ui.theme.SimpleRestTheme
import com.example.simplerest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()

            SimpleRestTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerContent(navController = navController, coroutineScope = coroutineScope, drawerState = drawerState)
                    },
                    gesturesEnabled = true
                ) {
                    //Content
                    ScaffoldContainer(
                        addButtonAction = { viewModel.addUser() },
                        iconButtonAction = { coroutineScope.launch { drawerState.open() }}
                    ){ paddingValues ->
                        //Nav Host
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") { HomeScreen(viewModel = viewModel, modifier = Modifier.padding(paddingValues)) }
                            composable("settings") { SettingsScreen(modifier = Modifier.padding(paddingValues)) }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldContainer(
    addButtonAction: () -> Unit = {},
    iconButtonAction: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            Toolbar (
                scrollBehavior = scrollBehavior,
                navigationIconAction = iconButtonAction,
                addButtonAction = addButtonAction
            )
        }
    ){
        content(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigationIconAction: () -> Unit = {},
    addButtonAction: () -> Unit = {}
){
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = addButtonAction) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(R.string.toolbar_btn_add_action), tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        navigationIcon = {
            IconButton(onClick = navigationIconAction) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = stringResource(R.string.toolbar_btn_menu_action), tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        scrollBehavior = scrollBehavior,
    )
}


@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    items: List<NavDrawerItem> = NavDrawerOptions.items,
    navController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalDrawerSheet{
        //Drawer Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(12.dp)
        ){
            Text(
                modifier = Modifier.padding(top = 50.dp),
                text = stringResource(id = R.string.app_name),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        //Drawer Body
        LazyColumn(modifier){
            items(items){item ->
                NavigationDrawerItem(
                    modifier = Modifier.padding(8.dp),
                    icon = {
                       Icon(
                           painter = painterResource(id = item.icon),
                           contentDescription = item.contentDescription
                       )
                    },
                    label = {
                        Text(
                            text = item.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        //Close Nav Drawer
                        coroutineScope.launch { drawerState.close() }

                        //Navigate if route has changed
                        if(currentRoute != item.route){
                            navController.navigate(item.route)
                        }
                    }
                )
            }
        }
    }
}