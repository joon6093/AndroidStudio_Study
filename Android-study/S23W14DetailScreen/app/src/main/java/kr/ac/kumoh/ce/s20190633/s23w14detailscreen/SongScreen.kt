package kr.ac.kumoh.ce.s20190633.s23w14detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class SongScreen {
    List,
    Detail
}

@Composable
fun SongApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SongScreen.List.name,  // 처음에 시작하는게 뭔지 List.name -> "List"
    ) {
        composable(route = SongScreen.List.name) {
            SongList(navController)
        }
        composable(
            route = SongScreen.Detail.name + "/{songId}",   // 마찬가지
            arguments = listOf(navArgument("songId") {
                type = NavType.StringType
            })
        ) {
            SongDetail(it.arguments?.getString("songId"))
        }
    }
}

@Composable
fun SongList(navController: NavController) {
    Column {
        Button(
            onClick = { navController.navigate(SongScreen.Detail.name + "/song01") }
        ) {
            Text(text = "노래 1")
        }
        Button(
            onClick = { navController.navigate(SongScreen.Detail.name + "/song02") }
        ) {
            Text(text = "노래 2")
        }
    }
}

@Composable
fun SongDetail(songId: String?) {
    Column {
        Text("노래 $songId")
        Text("가수 $songId")
    }
}