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

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val KEY_NAME = "mechanic"
        const val GUNDAM = "gundam"
        const val ZAKU = "zaku"
    }

    private lateinit var main: ActivityMainBinding
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult

        val result = it.data?.getIntExtra(
            ImageActivity.IMAGE_RESULT,
            ImageActivity.NONE) // 없을 때 반환값
        val str = when (result) {
            ImageActivity.LIKE -> "좋아요"
            ImageActivity.DISLIKE -> "싫어요"
            else -> ""
        }
        when (it.data?.getStringExtra(ImageActivity.IMAGE_NAME)) {
            GUNDAM -> main.btnGundam.text = "건담 ($str)"
            ZAKU -> main.btnZaku.text = "자쿠 ($str)"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.btnGundam.setOnClickListener(this)
        main.btnZaku.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, ImageActivity::class.java)
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
        intent.putExtra(KEY_NAME, value)
        //startActivity(intent)
        startForResult.launch(intent)
    }
}