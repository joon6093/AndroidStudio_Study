package kr.ac.kumoh.ce.s20190633.s23w06intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.ac.kumoh.ce.s20190633.s23w06intent.ui.theme.S23W06IntentTheme

// 두 번째 액티비티 클래스 정의
class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 앱의 테마를 설정하여 UI 컴포넌트들을 표시
            S23W06IntentTheme {
                // 백그라운드 색상을 테마의 'background' 색상으로 설정한 컨테이너
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // "Android"라는 텍스트로 인사말 컴포넌트를 표시
                    Greeting2("Android")
                }
            }
        }
    }
}

// 인사말 컴포넌트 정의
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    // "Hello [name]!" 형태의 텍스트를 표시
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// 프리뷰 함수: UI 디자인을 미리보기 환경에서 확인할 수 있게 해줌
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    S23W06IntentTheme {
        Greeting2("Android")
    }
}
