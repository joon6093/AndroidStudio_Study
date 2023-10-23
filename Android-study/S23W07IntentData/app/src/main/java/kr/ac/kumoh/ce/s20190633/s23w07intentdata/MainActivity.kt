package kr.ac.kumoh.ce.s20190633.s23w07intentdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.databinding.ActivityMainBinding

// 메인 액티비티 클래스 정의. 이 액티비티에서는 두 버튼(건담, 자쿠)을 제공하며, 각 버튼을 클릭하면
// 해당하는 이미지를 보여주는 ImageActivity로 이동한다.
class MainActivity : AppCompatActivity(), OnClickListener {

    // 동반 객체. 액티비티 간 데이터 전송에 사용되는 키와 값들을 상수로 정의한다.
    companion object {
        const val KEY_NAME = "mechanic"
        const val GUNDAM = "gundam"
        const val ZAKU = "zaku"
    }

    // 뷰 바인딩 객체. XML 레이아웃의 뷰와 연결된다.
    private lateinit var main: ActivityMainBinding

    // ImageActivity로부터 결과를 받아오기 위한 콜백 정의.
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        // 결과가 OK가 아닌 경우에는 아무 처리도 하지 않고 반환한다.
        if (it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult

        // ImageActivity에서 반환된 사용자의 평가(좋아요/싫어요)를 가져온다.
        val result = it.data?.getIntExtra(
            ImageActivity.IMAGE_RESULT,
            ImageActivity.NONE) // 결과가 없을 때 기본값

        // 사용자 평가를 문자열로 변환한다.
        val str = when (result) {
            ImageActivity.LIKE -> "좋아요"
            ImageActivity.DISLIKE -> "싫어요"
            else -> ""
        }
        // 선택된 메카닉 이름에 따라 해당하는 버튼의 텍스트를 업데이트한다.
        when (it.data?.getStringExtra(ImageActivity.IMAGE_NAME)) {
            GUNDAM -> main.btnGundam.text = "건담 ($str)"
            ZAKU -> main.btnZaku.text = "자쿠 ($str)"
        }
    }

    // 액티비티가 처음 생성될 때 호출되는 메서드.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 뷰 바인딩 객체를 초기화하고, 뷰를 액티비티에 연결한다.
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        // 건담 및 자쿠 버튼에 클릭 리스너를 설정한다. 클릭 시 ImageActivity로 이동한다.
        main.btnGundam.setOnClickListener(this)
        main.btnZaku.setOnClickListener(this)
    }

    // 버튼 클릭 시 호출되는 메서드.
    override fun onClick(p0: View?) {
        // ImageActivity로 이동하기 위한 Intent 객체를 생성한다.
        val intent = Intent(this, ImageActivity::class.java)

        // 클릭된 버튼에 따라 메카닉 이름을 설정하고, 해당하는 메카닉의 이름을 토스트 메시지로 보여준다.
        val value = when (p0?.id) {
            main.btnGundam.id -> {
                Toast.makeText(this, "건담 이미지", Toast.LENGTH_SHORT).show()
                GUNDAM
            }
            main.btnZaku.id -> {
                Toast.makeText(this, "자쿠 이미지", Toast.LENGTH_SHORT).show()
                ZAKU
            }
            else -> return
        }
        // 선택된 메카닉 이름을 Intent에 추가한다.
        intent.putExtra(KEY_NAME, value)

        // Intent를 사용하여 ImageActivity를 시작하고, 그 결과를 기다린다.
        startForResult.launch(intent)
    }
}
