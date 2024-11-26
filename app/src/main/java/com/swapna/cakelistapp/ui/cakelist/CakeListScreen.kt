package com.swapna.cakelistapp.ui.cakelist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.swapna.cakelistapp.data.model.CakeList
import com.swapna.cakelistapp.ui.base.UiState

@Composable
fun CakeListScreen(uiState: UiState<List<CakeList>>, onCakeClick: (url: String) -> Unit) {
    when(uiState){
        is UiState.Error -> {}
        UiState.Loading -> {}
        is UiState.Success -> {
            CakeListItem(onCakeClick, uiState.data)
        }
    }
}

@Composable
fun CakeListItem(onCakeClick: (url: String) -> Unit, data: List<CakeList>) {
    LazyColumn {
        items(data.size){
            CakeListItems(onCakeClick, data[it])
            if (it < data.lastIndex) {
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun CakeListItems(onCakeClick: (url: String) -> Unit, cakeList: CakeList) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable {
            if (cakeList.title.isNotEmpty())
                onCakeClick(cakeList.image)
        }){
        BannerImage(cakeList)
        CakeTitle(cakeList.title)
        CakeDescription(cakeList.desc)
    }
}

@Composable
fun CakeTitle(title: String) {
    Text(text = title,
         maxLines = 2,
         modifier = Modifier
             .padding(4.dp)
             .fillMaxWidth(),
         style = MaterialTheme.typography.headlineMedium
         )
}

@Composable
fun CakeDescription(desc: String) {
    Text(text = desc,
         maxLines = 2,
         style = MaterialTheme.typography.headlineSmall,
         modifier = Modifier
             .padding(4.dp)
             .fillMaxWidth())
}

@Composable
fun BannerImage(cakeList: CakeList) {
    AsyncImage(model = cakeList.image,
               contentDescription = cakeList.title,
               contentScale = ContentScale.Crop,
               modifier = Modifier.fillMaxWidth().height(200.dp)
               )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CakeListRoute(onCakeClick:(url:String)->Unit, cakeViewModel:CakeViewModel = hiltViewModel()){
    val uiState by cakeViewModel.cakeUiState.collectAsStateWithLifecycle()

    Scaffold(topBar ={
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White),
                  title = {

                  })
    },
             content = {paddingValues ->
                 Column(modifier = Modifier.padding(paddingValues)) {
                     CakeListScreen(uiState , onCakeClick )
                 }
             }
    )
}