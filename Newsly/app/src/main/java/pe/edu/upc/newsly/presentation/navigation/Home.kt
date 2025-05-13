package pe.edu.upc.newsly.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.newsly.domain.model.News
import pe.edu.upc.newsly.presentation.view.FavoriteNewsListView
import pe.edu.upc.newsly.presentation.view.NewsDetailView
import pe.edu.upc.newsly.presentation.view.NewsSearchView

@Preview
@Composable
fun Home() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        NavigationItem(icon = Icons.Default.Search, "Find news", "news_list"),
        NavigationItem(icon = Icons.Default.Favorite, "Favorites", "favorites"),

        )

    val selectedIndex = remember {
        mutableStateOf(0)
    }

    val selectedNews = remember {
        mutableStateOf<News?>(null)
    }

    Scaffold (
        bottomBar = {
            BottomAppBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex.value,
                        onClick = {
                            selectedIndex.value = index
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(item.title)
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        }
                    )
                }
            }
        }
    ){ padding ->
        NavHost(navController, startDestination = "news_list", modifier = Modifier.padding(padding) ) {
            composable("news_list") {
                NewsSearchView { news ->
                    selectedNews.value = news
                    navController.navigate("news_detail")
                }
            }
            composable("news_detail") {
                selectedNews.value?.let { it ->
                    NewsDetailView(news = it)

                }
            }
            composable("favorites") {
                FavoriteNewsListView()
            }

        }
    }
}

data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val route: String

)