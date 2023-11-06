package kr.ac.kumoh.ce.s20190633.s23w04carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20190633.s23w04carddealer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardDealerViewModel
    private var simulationDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        model = ViewModelProvider(this)[CardDealerViewModel::class.java]

        model.cards.observe(this, Observer {
            val res = IntArray(5)
            for (i in it.indices) {
                // 카드 리소스 식별자를 가져와 배열에 저장
                res[i] = resources.getIdentifier(
                    getCardName(it[i]),
                    "drawable",
                    packageName
                )
            }
            main.card1.setImageResource(res[0])
            main.card2.setImageResource(res[1])
            main.card3.setImageResource(res[2])
            main.card4.setImageResource(res[3])
            main.card5.setImageResource(res[4])
        })
        // 족보 결과 텍스트 뷰 업데이트
        model.handType.observe(this, Observer {
            main.tvHandType.text = it
        })

        // '한 판만' 버튼 클릭 이벤트 설정
        main.btnShuffle.setOnClickListener {
            model.shuffle()
        }
        // 시뮬레이션 결과 LiveData 관찰 및 업데이트
        model.simulationResult.observe(this, Observer { result ->
            // 시뮬레이션 다이얼로그 설정
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

            // 시뮬레이션 진행 중 여부에 따라 버튼 텍스트 설정
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

        // 시뮬레이션 버튼 클릭 이벤트 설정
        main.btnSimulation.setOnClickListener {
            // 시뮬레이션 횟수 입력 다이얼로그 생성 및 표시
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("시뮬레이션")
            dialog.setMessage("시뮬레이션 횟수를 입력하세요:")

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
        // -1일 경우 조커 카드로 가정하고 "c_red_joker" 반환
        if (c == -1) {
            return "c_red_joker"
        }
        // 카드 모양에 따른 이름 결정
        var shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        // 카드 숫자에 따른 이름 결정
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

        // 최종 카드 이름 반환
        return "c_${number}_of_${shape}"
    }

}
