// 패키지 정의
package kr.ac.kumoh.ce.s20190633.s23w04carddealer

// 필요한 Android 라이브러리와 클래스들을 임포트
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20190633.s23w04carddealer.databinding.ActivityMainBinding

// MainActivity 정의. AppCompatActivity를 상속 받음
class MainActivity : AppCompatActivity() {
    // 뷰 바인딩 변수와 ViewModel 변수 선언
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardDealerViewModel

    // 액티비티가 생성될 때 호출되는 콜백 메서드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩을 사용하여 레이아웃 파일을 설정
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        // ViewModel 인스턴스를 가져옴
        model = ViewModelProvider(this)[CardDealerViewModel::class.java]

        // ViewModel의 cards를 관찰하고 변화가 있을 때 UI 업데이트
        model.cards.observe(this, Observer {
            val res = IntArray(5)
            for (i in it.indices) {
                Log.i("it:", it[i].toString())
                Log.i("getCardName:", getCardName(it[i]).toString())
                res[i] = resources.getIdentifier(
                    getCardName(it[i]),
                    "drawable",
                    packageName
                )
                Log.i("res:", res[i].toString())
            }
            // 첫 번째 카드 이미지를 설정
            main.card1.setImageResource(res[0])
            // 아래는 주석 처리된 다른 카드들의 이미지 설정 코드
             main.card2.setImageResource(res[1])
             main.card3.setImageResource(res[2])
             main.card4.setImageResource(res[3])
             main.card5.setImageResource(res[4])
        })

        // '셔플' 버튼을 클릭했을 때의 동작 설정
        main.btnShuffle.setOnClickListener {
            model.shuffle()
        }
    }

    // 카드 번호를 받아 해당하는 카드 이름(문자열)을 반환하는 함수
    private fun getCardName(c: Int) : String {
        // 주어진 값이 -1이면 조커 카드를 의미한다고 가정하고 바로 "c_red_joker"를 반환
        if (c == -1) {
            return "c_red_joker"
        }
        // 카드 모양에 따른 이름을 결정
        var shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        // 카드 숫자에 따른 이름을 결정
        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> {
                shape = shape.plus("2")
                "jack"
            }
            11 -> {
                shape = shape.plus("2")
                "queen"
            }
            12 -> {
                shape = shape.plus("2")
                "king"
            }
            else -> "error"
        }

        // 카드 이름을 반환
        return "c_${number}_of_${shape}"
    }
}
