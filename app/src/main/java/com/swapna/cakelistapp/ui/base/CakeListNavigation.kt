package com.swapna.cakelistapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swapna.cakelistapp.data.model.CakeList
import com.swapna.cakelistapp.ui.cakelist.CakeListRoute


@Composable
fun CakeListNavigation() {
    val localContext = LocalContext.current
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.cakeList.name) {
        composable(route = Route.cakeList.name){
            CakeListRoute(onCakeClick = {
                openCustomTab("",localContext)
            })
        }
    }
}

sealed class Route (val name:String){
    object cakeList : Route("CakeList")
}

fun openCustomTab(url:String, context: Context){
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}