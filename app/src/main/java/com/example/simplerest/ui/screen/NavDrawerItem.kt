package com.example.simplerest.ui.screen

import com.example.simplerest.R

data class NavDrawerItem(
    val title: String,
    val icon: Int,
    val contentDescription: String?,
    val route: String,
)

object NavDrawerOptions{
    val items = arrayListOf(
        NavDrawerItem(
            title = "Home",
            route = "home",
            icon = R.drawable.baseline_home_24,
            contentDescription = "Home Item"
        ),
        NavDrawerItem(
            title = "Settings",
            route = "settings",
            icon = R.drawable.baseline_settings_24,
            contentDescription = "Settings Item"
        )
    )
}