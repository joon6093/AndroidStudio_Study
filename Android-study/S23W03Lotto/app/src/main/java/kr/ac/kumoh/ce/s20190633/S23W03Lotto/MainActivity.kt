// 패키지 정의
package kr.ac.kumoh.ce.s20190633.S23W03Lotto

// 필요한 Android 라이브러리와 클래스들을 임포트
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20190633.S23W03Lotto.databinding.ActivityMainBinding

// MainActivity 정의. AppCompatActivity를 상속 받음
class MainActivity : AppCompatActivity() {
    // 뷰 바인딩, 뷰모델 및 텍스트뷰 배열에 대한 멤버 변수 선언
    private lateinit var main: ActivityMainBinding
    private lateinit var model: LottoViewModel
    private lateinit var txtNum: Array<TextView?>

    // 액티비티가 생성될 때 호출되는 콜백 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Lifecycle!!!", "onCreate") // 로그 메시지로 생명주기 확인
        //setContentView(R.layout.activity_main)  ->  setContentView(R.layout.activity_main)은 액티비티에 activity_main.xml 레이아웃 파일의 내용을 화면에 표시하도록 설정하는 것입니다.
        // 뷰 바인딩을 사용하여 레이아웃 파일을 설정
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        // 텍스트뷰 배열을 뷰 바인딩으로 초기화
        txtNum = arrayOf(main.num1, main.num2, main.num3, main.num4, main.num5, main.num6)

        // 뷰모델 인스턴스 초기화
        model = ViewModelProvider(this)[LottoViewModel::class.java]

        // 로또 번호가 변경될 때마다 UI 업데이트
        model.numbers.observe(this, Observer {
            Log.i("Observer!!!", "numbers changed")
            setNumbersText()
        })

        // 버튼 클릭시 로또 번호 생성
        main.btnGenerate.setOnClickListener {
            model.generate()
        }
    }

    // 로또 번호 텍스트뷰를 뷰모델의 로또 번호 값으로 설정하는 메서드
    private fun setNumbersText() {
        txtNum.forEachIndexed { index, textView ->
            textView?.text = model.numbers.value!![index].toString()
        }
    }

    // 액티비티의 생명주기 메서드들. 각 메서드는 로그 메시지를 사용하여 생명주기 상태를 확인
    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle!!!", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle!!!", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle!!!", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle!!!", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle!!!", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle!!!", "onDestroy")
    }
}
