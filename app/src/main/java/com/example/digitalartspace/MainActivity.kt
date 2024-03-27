package com.example.digitalartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitalartspace.ui.theme.DigitalArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    var image by rememberSaveable { mutableIntStateOf(0) }
    var location by rememberSaveable { mutableIntStateOf(R.string.photo_one) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Picture(painterResource(findPic(image)))
        Spacer(modifier = Modifier.height(30.dp))
        Details(location,"klok")
        Spacer(modifier = Modifier.height(50.dp))
        FactionalButtons(
            onNext = {
                if (image != 2) {
                    image += 1
                }else{
                    image = 0
                }
                location=updateLocation(image)
            },
            onBack = {
                if (image != 0) {
                    image -= 1
                }else{
                    image = 2
                }
                location=updateLocation(image)
            }
        )
    }
}
fun findPic(id:Int): Int {
    return when (id) {
        0 -> R.drawable.kavala
        1 -> R.drawable.kavalahigh
        else -> R.drawable.snow
    }
}
fun updateLocation(id:Int): Int {
    return when (id) {
        0 -> R.string.photo_one
        1 -> R.string.photo_two
        else -> R.string.photo_three
    }
}


@Composable
fun Picture(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 30.dp, start = 10.dp, end = 10.dp),
    ) {
        Surface(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
                .align(Alignment.Center)
                .shadow(4.dp, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
        ) {
            Image(
                painter = painter, contentDescription = null,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun Details(
    location: Int,
    photographer: String,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = Modifier
            .wrapContentWidth(),
        color = Color.LightGray,
        shape = RoundedCornerShape(5.dp)
    ){
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Text(modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 5.dp),
                fontSize = 30.sp,
                text = stringResource(location)
            )
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = photographer
            )
        }
    }
}

@Composable
fun FactionalButtons(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier=Modifier
        .padding(bottom = 20.dp)
        .fillMaxHeight(),
        verticalAlignment = Alignment.Bottom) {
        Button(
            onClick =  onNext
        ) {
            Text(
                fontSize = 18.sp,
                text = "back"
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.5f))
        Button(onClick =  onBack ) {
            Text(
                fontSize = 18.sp,
                text = "Next"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DigitalArtSpaceTheme {
        App()
    }
}