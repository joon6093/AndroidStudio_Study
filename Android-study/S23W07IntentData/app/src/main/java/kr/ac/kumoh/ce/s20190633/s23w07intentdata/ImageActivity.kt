package kr.ac.kumoh.ce.s20190633.s23w07intentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.ce.s20190633.s23w07intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var main: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityImageBinding.inflate(layoutInflater)
        setContentView(main.root)

        val res = when (intent.getStringExtra(MainActivity.KEY_NAME)) {
            MainActivity.GUNDAM -> R.drawable.gundam
            MainActivity.ZAKU -> R.drawable.zaku
            else -> R.drawable.ic_launcher_foreground
        }
        main.image.setImageResource(res)
    }
}