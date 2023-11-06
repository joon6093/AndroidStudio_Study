package kr.ac.kumoh.ce.s20190633.s23w04carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.random.Random
import kotlinx.coroutines.*

// 카드 딜러 앱의 ViewModel 정의
class CardDealerViewModel : ViewModel() {
    // 초기로 -1을 가짐 -> 조커 카드
    private var _cards = MutableLiveData<IntArray>(intArrayOf(-1, -1, -1, -1, -1))
    val cards: LiveData<IntArray>
        get() = _cards

    // 포커 족보를 저장할 LiveData 추가
    private var _handType = MutableLiveData<String>("")
    val handType: LiveData<String>
        get() = _handType

    // 카드를 셔플하는 함수
    fun shuffle() {
        var num = 0
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
        // 족보 결정 및 업데이트
        _handType.value = determineHand(newCards)
    }

    // 카드 배열을 받아 포커 족보를 결정하는 함수
    private fun determineHand(cards: IntArray): String {
        val suits = Array(4) { 0 }  // 각 카드 무늬별 개수
        val values = Array(13) { 0 }  // 각 카드 숫자별 개수

        // 카드들을 순회하며 무늬와 숫자별 개수를 계산
        for (card in cards) {
            suits[card / 13]++
            values[card % 13]++
        }

        // 중복되지 않는 숫자의 개수와 가장 많은 숫자 카드의 개수를 확인
        val distinctValues = values.filter { it > 0 }.size
        val maxOfValues = values.maxOrNull() ?: 0

        // 족보 판별 로직
        return when {
            hasRoyalStraight(values) && hasFlush(suits) -> "로얄 플러시"
            hasStraight(values) && hasFlush(suits) -> "스트레이트 플러시"
            maxOfValues == 4 -> "포 카드"
            maxOfValues == 3 && distinctValues == 2 -> "풀 하우스"
            hasFlush(suits) -> "플러시"
            hasStraight(values) -> "스트레이트"
            maxOfValues == 3 -> "트리플"
            maxOfValues == 2 && distinctValues == 3 -> "투 페어"
            maxOfValues == 2 && distinctValues == 4 -> "원 페어"
            else -> "하이 카드"
        }
    }

    // 로얄 스트레이트 여부 확인 함수
    private fun hasRoyalStraight(values: Array<Int>): Boolean {
        return values[0] > 0 && values[1] > 0 && values[10] > 0 && values[11] > 0 && values[12] > 0
    }

    // 스트레이트 여부 확인 함수
    private fun hasStraight(values: Array<Int>): Boolean {
        // 일반 스트레이트 확인
        for (i in 0 until 9) {
            if (values.slice(i until i + 5).all { it > 0 }) {
                return true
            }
        }

        // A, 2, 3, 4, 5 스트레이트 확인
        if (values[0] > 0 && values.slice(1 until 5).all { it > 0 }) {
            return true
        }

        return false
    }

    // 플러시 여부 확인 함수
    private fun hasFlush(suits: Array<Int>): Boolean {
        return suits.any { it == 5 }
    }

    // 시뮬레이션 결과를 저장할 LiveData
    private var _simulationResult = MutableLiveData<String>()
    val simulationResult: LiveData<String>
        get() = _simulationResult

    // 시뮬레이션을 위한 코루틴 Job 변수
    private var simulationJob: Job? = null

    // 주어진 횟수만큼 족보 시뮬레이션을 실행하는 함수
    fun simulate(times: Int) {
        simulationJob = viewModelScope.launch {
            val handCounts = mutableMapOf<String, Int>()
            var results = ""
            // 지정된 횟수만큼 족보를 생성하고 통계를 계산
            for (i in 0 until times) {
                shuffle()
                delay(300) // 시각적 효과를 위한 지연

                val hand = _handType.value ?: "Error"
                handCounts[hand] = handCounts.getOrDefault(hand, 0) + 1

                // 마지막 반복에서만 확률 계산
                if (i == times - 1) {
                    results = handCounts.entries.joinToString("\n") { (hand, count) ->
                        val probability = count.toDouble() / times * 100
                        "$hand:${count}회 -> ${"%.1f".format(probability)}%"
                    }
                } else {
                    // 시뮬레이션이 진행 중일 때는 확률 계산 없이 결과만 업데이트
                    results = handCounts.entries.joinToString("\n") { (hand, count) ->
                        "$hand:${count}회"
                    }
                }
                _simulationResult.postValue(results)
            }
        }
    }

    // 시뮬레이션 취소 함수
    fun cancelSimulation() {
        simulationJob?.cancel()
    }

    // 시뮬레이션이 진행 중인지 확인하는 함수
    fun isSimulationInProgress(): Boolean {
        return simulationJob?.isActive == true
    }
}
