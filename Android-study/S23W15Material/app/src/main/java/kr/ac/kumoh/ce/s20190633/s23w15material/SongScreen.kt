package kr.ac.kumoh.ce.s20190633.s23w15material

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

enum class SongScreen {
    SongList,
    SingerList
}

@Composable
fun SongDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SongDrawerSheet(drawerState)
        },
        gesturesEnabled = true,
    ) {

    }
}

@Composable
fun SongDrawerSheet(
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    ModalDrawerSheet {
        NavigationDrawerItem(
            icon = { SongIcon() },
            label = { Text("노래 리스트") },
            selected = false,
            onClick = {
                scope.launch {
                    drawerState.close()
                }
            }
        )
        NavigationDrawerItem(
            icon = { SingerIcon() },
            label = { Text("가수 리스트") },
            selected = false,
            onClick = {
                scope.launch {
                    drawerState.close()
                }
            }
        )
    }
}

@Composable
fun SongIcon() {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "노래"
    )
}

@Composable
fun SingerIcon() {
    Icon(
        imageVector = Icons.Default.Face,
        contentDescription = "가수"
    )
}