package kr.ac.kumoh.ce.s20190633.s23w06intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import kr.ac.kumoh.ce.s20190633.s23w06intent.ui.theme.S23W06IntentTheme

// 메인 액티비티 클래스 정의
class MainActivity : ComponentActivity() {
    // 액티비티가 생성될 때 호출되는 함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 앱의 테마를 설정하여 UI 컴포넌트들을 표시
            S23W06IntentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // 테마의 배경색으로 설정
                ) {
                    Greeting("Test")
                }
            }
        }
    }
}

// 인사말과 여러 버튼을 포함하는 컴포넌트 정의
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current as Activity// 현재의 Context를 가져옴

    Column(
        modifier = modifier
    ) {
        // 각 버튼마다 서로 다른 기능을 수행하는 Intent를 실행
        // 유튜브 링크를 열기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("https://www.youtube.com/results?search_query=android+developers")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("유튜브")
        }
        // 인스타그램 링크를 열기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("https://www.instagram.com/newjeans_official/")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("인스타그램")
        }
        // 깃허브 링크를 열기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("https://github.com/joon6093")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("깃허브")
        }
        // 특정 좌표로 지도를 열기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("geo:36.145014,128.393047?z=17")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("좌표")
        }
        // 주소나 상호명으로 지도를 열기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("geo:0,0?q=금오공과대학교")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("주소 또는 상호")
        }
        // 전화를 걸기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("tel:0540001234")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(context, intent, null)
            }
        ) {
            Text("전화")
        }
        // 문자를 보내기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val uri = Uri.parse("sms:01000001234")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.putExtra("sms_body", "전화 부탁드립니다") // 문자 내용 설정
                startActivity(context, intent, null)
            }
        ) {
            Text("문자")
        }
        // SecondActivity로 이동하기 위한 버튼
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                startActivity(context, intent, null)
            }
        ) {
            Text("SecondActivity")
        }
    }
}

// 프리뷰 함수: UI 디자인을 미리보기 환경에서 확인할 수 있게 해줌
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S23W06IntentTheme {
        Greeting("Test")
    }
}
