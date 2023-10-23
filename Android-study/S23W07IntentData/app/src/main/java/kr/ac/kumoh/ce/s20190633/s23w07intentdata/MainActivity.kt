package kr.ac.kumoh.ce.s20190633.s23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_NAME = "mechanic"
    }

    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.btnGundam.setOnClickListener {
            Toast.makeText(this, "건담 이미지", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(KEY_NAME, "gundam")
            startActivity(intent)
        }

        main.btnZaku.setOnClickListener {
            Toast.makeText(this, "자쿠 이미지", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(KEY_NAME, "zaku")
            startActivity(intent)
        }
    }
}