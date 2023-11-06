// 패키지 정의
package kr.ac.kumoh.ce.s20190633.s23w04carddealer

// 필요한 Android 라이브러리와 클래스들을 임포트
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20190633.s23w04carddealer.databinding.ActivityMainBinding

// MainActivity 정의. AppCompatActivity를 상속 받음
class MainActivity : AppCompatActivity() {
    // 뷰 바인딩 변수와 ViewModel 변수 선언
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardDealerViewModel
    private var simulationDialog: AlertDialog? = null
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
//                Log.i("it:", it[i].toString())
//                Log.i("getCardName:", getCardName(it[i]).toString())
                res[i] = resources.getIdentifier(
                    getCardName(it[i]),
                    "drawable",
                    packageName
                )
//                Log.i("res:", res[i].toString())
            }
            // 카드 이미지를 설정
            main.card1.setImageResource(res[0])
            main.card2.setImageResource(res[1])
            main.card3.setImageResource(res[2])
            main.card4.setImageResource(res[3])
            main.card5.setImageResource(res[4])
        })
        model.handType.observe(this, Observer {
            main.tvHandType.text = it
        })

        // '셔플' 버튼을 클릭했을 때의 동작 설정
        main.btnShuffle.setOnClickListener {
            model.shuffle()
        }
        model.simulationResult.observe(this, Observer { result ->
            if (simulationDialog == null) {
                simulationDialog = AlertDialog.Builder(this)
                    .setTitle("시뮬레이션 결과")
                    .setMessage(result)
                    .setNegativeButton("취소") { dialog, _ ->
                        model.cancelSimulation()
                        dialog.dismiss()
                    }
                    .create()
            } else {
                simulationDialog?.setMessage(result)
            }

            // 시뮬레이션이 진행 중인지 여부에 따라 버튼 텍스트 설정
            val isSimulationInProgress = model.isSimulationInProgress()
            simulationDialog?.getButton(AlertDialog.BUTTON_NEGATIVE)?.text =
                if (isSimulationInProgress) "취소" else "확인"
            simulationDialog?.show()

            // 다이얼로그 위치 조정
            simulationDialog?.window?.let { window ->
                val layoutParams = window.attributes
                layoutParams.gravity = Gravity.BOTTOM
                window.attributes = layoutParams
            }

        })

        main.btnSimulation.setOnClickListener {
            // 다이얼로그 생성 및 입력 받기
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("시뮬레이션")
            dialog.setMessage("시뮬레이션 횟수를 입력하세요")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            dialog.setView(input)

            dialog.setPositiveButton("확인") { _, _ ->
                val simulationCount = input.text.toString().toIntOrNull() ?: 0
                model.simulate(simulationCount)
            }

            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }

            dialog.show()
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
