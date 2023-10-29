package kr.ac.kumoh.ce.s20190633.s23w07intentdata

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.databinding.ActivityImageBinding

// 이미지 평가를 위한 액티비티 클래스 정의
class ImageActivity : AppCompatActivity(), OnClickListener {

    // 동반 객체(Companion Object): 정적 변수나 함수를 정의할 때 사용
    companion object {
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"

        // 이미지 평가 상수 정의
        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }

    // 액티비티에서 사용할 뷰 바인딩 객체 선언
    private lateinit var main: ActivityImageBinding

    // 선택된 메카닉의 이름을 저장할 변수 선언
    private lateinit var mechanic: String

    // 액티비티가 생성될 때 호출되는 함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩 초기화
        main = ActivityImageBinding.inflate(layoutInflater)
        setContentView(main.root)

        // MainActivity로부터 전달받은 메카닉 이름을 가져옴
        mechanic = intent.getStringExtra(MainActivity.KEY_NAME) ?: "none"

        // 메카닉 이름에 따른 이미지 리소스 결정
        val res = when (mechanic) {
            MainActivity.GUNDAM -> R.drawable.gundam
            MainActivity.ZAKU -> R.drawable.zaku
            else -> R.drawable.ic_launcher_foreground
        }

        // 이미지 뷰에 리소스 설정
        main.image.setImageResource(res)

        // 좋아요와 싫어요 버튼에 클릭 리스너 설정
        main.btnLike.setOnClickListener(this)
        main.btnDislike.setOnClickListener(this)
    }

    // 버튼 클릭 시 호출되는 함수
    override fun onClick(v: View?) {  // -> 펑셔널 인터페이스 , SAM -> Single Abstract Method
        // 결과를 반환하기 위한 Intent 객체 생성
        val intent = Intent()
        // 클릭된 버튼에 따라 평가 값 설정
        val value = when (v?.id) {
            main.btnLike.id -> {
                LIKE
            }
            main.btnDislike.id -> {
                DISLIKE
            }
            else -> {
                NONE
            }
        }

        // 선택된 메카닉 이름과 평가 값을 Intent에 추가
        intent.putExtra(IMAGE_NAME, mechanic)
        intent.putExtra(IMAGE_RESULT, value)

        // 결과를 MainActivity에 전달하고 액티비티 종료
        setResult(RESULT_OK, intent)
        finish()
    }
}
