package kr.ac.kumoh.ce.s20190633.s23midterms_explanation

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.In
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kr.ac.kumoh.ce.s20190633.s23midterms_explanation.ui.theme.S23midterms_explanationTheme

class SecondActivity : ComponentActivity() {
    companion object {
        const val BUTTON_PRESSED = "ButtonPressed"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S23midterms_explanationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(intent./*(13)*/getIntExtra(MainActivity.NEUVI_KEY,MainActivity.NEUVI_NONE)){
                        MainActivity.NEUVI_IMAGE -> NeuvilletteImage()
                        MainActivity.NEUVI_BUTTON -> NeuvilletteButton()
                        else -> finish ()
                    }
                }
            }
        }
    }
}

@Composable
fun NeuvilletteImage(){
    Image(/*(14)*/painter =
    /*(14)*/painterResource(id = R.drawable.neuvillette),
        contentDescription = "neuvillette Image"
    )
}
@Composable
fun NeuvilletteButton(){
    val activity = LocalContext. current/*(15)*/as Activity
    Button(/*(16)*/onClick ={
        val result = /*(17)*/Intent()
        result./*(8)*/putExtra(SecondActivity. BUTTON_PRESSED,true)
        activity. setResult (RESULT_OK, result)
        activity.finish()
    }){
        Text ("Neuvillette")
    }

}

