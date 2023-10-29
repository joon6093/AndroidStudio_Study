// 패키지 정의
package com.example.w0102thefirst

// 필요한 Android 라이브러리와 클래스들을 임포트
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.w0102thefirst.databinding.ActivityMainBinding

// MainActivity 정의. AppCompatActivity를 상속 받음
class MainActivity : AppCompatActivity() {
    // 뷰 바인딩 변수 선언
    private lateinit var main: ActivityMainBinding
    // 카운터 값을 저장할 변수 초기화
    private var count = 0

    // 액티비티가 생성될 때 호출되는 콜백 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        // 뷰 바인딩을 사용하여 레이아웃 파일을 설정
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        // '더하기' 버튼을 클릭했을 때의 동작 설정
        main.btnAdd.setOnClickListener {
            // 카운터 값을 증가시키고 텍스트뷰에 반영
            main.txtCount.text = "${++count}"
        }

        // '빼기' 버튼을 클릭했을 때의 동작 설정
        main.btnSub.setOnClickListener {
            // 카운터 값이 0보다 크면 감소시키고 텍스트뷰에 반영
            if (count > 0)
                main.txtCount.text = "${--count}"
        }

        // '리셋' 버튼을 클릭했을 때의 동작 설정
        main.btnReset.setOnClickListener {
            // 카운터 값을 0으로 초기화하고 텍스트뷰에 반영
            count = 0
            main.txtCount.text = "$count"
        }
    }
}
