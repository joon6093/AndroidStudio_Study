package kr.ac.kumoh.ce.s20190633.s23midterms_explanation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.ce.s20190633.s23midterms_explanation.databinding.ActivityExamBinding

class MainActivity : AppCompatActivity(), /*(1)*/OnClickListener {
    companion object {
        const val  NEUVI_KEY = "KeyName"
        const val NEUVI_IMAGE = 100
        const val NEUVI_BUTTON = 200
        const val NEUVI_NONE = 0
    }
    private lateinit var  view: /*(2)*/ActivityExamBinding
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        // 결과가 OK가 아닌 경우에는 아무 처리도 하지 않고 반환한다.
        if (it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult
        val pressed = it.data?./*(3)*/getBooleanExtra(
            SecondActivity.BUTTON_PRESSED,false) ?: false
        if (/*(4)*/pressed)
            view.btnFourth.text=
                view.btnFourth.text.toString().plus("(눌림)")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = /*(2)*/ActivityExamBinding.inflate(layoutInflater)
        setContentView((/*(5)*/view.root))
        view.btnThird.setOnClickListener(/*(6)*/this)
        view.btnFourth.setOnClickListener(/*(6)*/this)
    }
    override fun /*(7)*/onClick(v: View?) {
        val second = Intent(this,SecondActivity::class.java)
        when(v?.id){
            view.btnThird.id -> second./*(8)*/putExtra(NEUVI_KEY, NEUVI_IMAGE)
            view.btnFourth.id -> second./*(8)*/putExtra(NEUVI_KEY, NEUVI_BUTTON)
        }
        startForResult./*(9)*/launch(second)
    }
}