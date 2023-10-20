// 패키지 정의
package kr.ac.kumoh.ce.s20190633.s23w04carddealer

// 필요한 Android 라이브러리와 클래스들을 임포트
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

// 카드 딜러 앱의 ViewModel 정의
class CardDealerViewModel : ViewModel() {
    // _cards는 내부에서만 변경 가능한 LiveData로서, 초기 값으로 -1을 가진 5개의 정수 배열을 갖는다.
    private var _cards = MutableLiveData<IntArray>(IntArray(5) { -1 })

    // cards는 외부에서 읽기 전용으로 접근 가능한 LiveData이다.
    val cards: LiveData<IntArray>
        get() = _cards

    // 카드를 셔플하는 함수
    fun shuffle() {
        var num = 0
        // 셔플된 새 카드를 저장할 배열을 생성. 초기 값으로 -1을 가진다.
        val newCards = IntArray(5) { -1 }

        // 각 카드 위치에 대해
        for (i in newCards.indices) {
            // 중복되지 않는 카드 번호를 생성하기 위한 반복문
            do {
                // 0부터 51 사이의 무작위 수(총 52개의 카드)를 생성
                num = Random.nextInt(52)
            } while (newCards.contains(num)) // 이미 선택된 카드 번호인 경우 다시 무작위 수를 생성

            // 셔플된 카드 번호를 배열에 저장
            newCards[i] = num
        }

        // 셔플된 카드 번호를 오름차순으로 정렬
        newCards.sort()

        // 셔플된 카드 번호를 _cards에 저장하여 LiveData를 업데이트
        _cards.value = newCards
    }
}
