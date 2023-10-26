package kr.ac.kumoh.ce.s20190633.s23w07intentdata

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.AnotherActivity.Companion.DISLIKE
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.AnotherActivity.Companion.IMAGE_NAME
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.AnotherActivity.Companion.IMAGE_RESULT
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.AnotherActivity.Companion.LIKE
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.ui.theme.S23W07IntentDataTheme

class AnotherActivity : ComponentActivity() {
    companion object {
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"

        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S23W07IntentDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MechanicImage()
                }
            }
        }
    }
}

// https://stackoverflow.com/questions/69234880/how-to-get-intent-data-in-a-composable
//fun Context.findActivity(): Activity? = when (this) {
//    is Activity -> {
//        Log.i("findActivity()!!!", "Activity")
//        this
//    }
//    is ContextWrapper -> {
//        Log.i("findActivity()!!!", "ContextWrapper")
//        baseContext.findActivity()
//    }
//    else -> null
//}

fun onResultClick(activity: Activity, imageName: String?, imageResult: Int) {
    val intent = Intent()
    intent.putExtra(IMAGE_NAME, imageName)
    intent.putExtra(IMAGE_RESULT, imageResult)
    activity.setResult(RESULT_OK, intent)
    activity.finish()
}

@Composable
fun MechanicImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as Activity //우린 액티비티인게 학실하므로 해당 방법으로 구현 (만약 확실하지않을 경우 위에 주석과 같이 해야함)
    val intent = activity.intent
    val imageName = intent.getStringExtra(MainActivity.KEY_NAME)
    val res = when (imageName) {
        MainActivity.GUNDAM -> R.drawable.gundam
        MainActivity.ZAKU -> R.drawable.zaku
        else -> R.drawable.ic_launcher_foreground
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = res),
        contentScale = ContentScale.Crop,
        contentDescription = "Mechanic Image"
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            onResultClick(activity, imageName, LIKE)
        }) {
            Text("좋아요")
        }

        Button(onClick = {
            onResultClick(activity, imageName, DISLIKE)
        }) {
            Text("싫어요")
        }
    }
}