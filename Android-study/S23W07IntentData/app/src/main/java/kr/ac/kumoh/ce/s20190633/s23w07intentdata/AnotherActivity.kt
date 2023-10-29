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

// AnotherActivity 정의 시작
class AnotherActivity : ComponentActivity() {
    companion object {
        // 고유 상수값 정의
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"
        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }

    // 액티비티가 생성될 때 호출되는 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S23W07IntentDataTheme {
                // UI 표면을 정의. 기본적으로 전체 화면 크기로 설정
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MechanicImage() // MechanicImage Composable 호출
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

// 기계 이미지 및 좋아요/싫어요 버튼을 포함하는 Composable 정의
@Composable
fun MechanicImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current // 현재 Composable의 Context 획득
    val activity = context as Activity // 현재 Context를 Activity로 형변환
    val intent = activity.intent // 현재 액티비티의 인텐트 정보 획득
    val imageName = intent.getStringExtra(MainActivity.KEY_NAME) // 인텐트로부터 이미지 이름 정보 획득
    // 이미지 이름에 따른 리소스 ID를 결정
    val res = when (imageName) {
        MainActivity.GUNDAM -> R.drawable.gundam
        MainActivity.ZAKU -> R.drawable.zaku
        else -> R.drawable.ic_launcher_foreground
    }

    // 이미지를 화면에 표시
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = res),
        contentScale = ContentScale.Crop,
        contentDescription = "Mechanic Image"
    )
    // 좋아요 및 싫어요 버튼을 수평으로 배치하는 Row Composable
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // 좋아요 버튼
        Button(onClick = {
            onResultClick(activity, imageName, LIKE)
        }) {
            Text("좋아요")
        }
        // 싫어요 버튼
        Button(onClick = {
            onResultClick(activity, imageName, DISLIKE)
        }) {
            Text("싫어요")
        }
    }
}
// 결과를 설정하고 액티비티를 종료하는 함수
fun onResultClick(activity: Activity, imageName: String?, imageResult: Int) {
    val intent = Intent()
    intent.putExtra(IMAGE_NAME, imageName)
    intent.putExtra(IMAGE_RESULT, imageResult)
    activity.setResult(RESULT_OK, intent)
    activity.finish()
}
