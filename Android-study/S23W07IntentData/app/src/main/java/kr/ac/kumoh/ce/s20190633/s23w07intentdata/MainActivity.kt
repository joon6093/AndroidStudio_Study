package kr.ac.kumoh.ce.s20190633.s23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val KEY_NAME = "mechanic"
        const val GUNDAM = "gundam"
        const val ZAKU = "zaku"
    }

    private lateinit var main: ActivityMainBinding
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
        startActivity(intent)
    }
}