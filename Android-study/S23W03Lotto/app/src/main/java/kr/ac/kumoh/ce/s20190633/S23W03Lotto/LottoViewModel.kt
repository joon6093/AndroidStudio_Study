// 패키지 정의
package kr.ac.kumoh.ce.s20190633.S23W03Lotto

// 필요한 Android 및 Kotlin 라이브러리와 클래스들을 임포트
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

// ViewModel 클래스를 상속 받아 정의된 LottoViewModel 클래스
class LottoViewModel : ViewModel() {

    // MutableLiveData는 변경 가능한 데이터를 저장하며, UI 컴포넌트와 데이터의 동기화를 돕습니다.
    private var _numbers:MutableLiveData<IntArray> = MutableLiveData<IntArray>(IntArray(6) { 0 })

    // numbers는 _numbers의 변경 불가능한 버전으로, UI에서 이를 관찰하게 됩니다.
    val numbers: LiveData<IntArray>
        get() = _numbers

    // 로또 번호를 생성하는 메서드
    fun generate() {
        var num = 0
        val newNumbers = IntArray(6) { 0 } // 새로운 로또 번호를 저장할 배열 초기화

        // 각 로또 번호에 대해
        for (i in newNumbers.indices) {
            // 중복 검사
            do {
                // 1부터 45 사이의 임의의 번호 생성
                num = Random.nextInt(1, 46)
            } while (newNumbers.contains(num)) // 이미 배열에 존재하는 번호인 경우 다시 생성
            newNumbers[i] = num
        }

        // 로또 번호 배열을 정렬
        newNumbers.sort()

        // _numbers MutableLiveData에 새로운 로또 번호 배열을 할당
        _numbers.value = newNumbers
    }
}
