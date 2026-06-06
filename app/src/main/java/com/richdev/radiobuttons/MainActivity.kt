package com.richdev.radiobuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.richdev.radiobuttons.ui.theme.RadioButtonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioButtonsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RadioButtonExample()
                }
            }
        }
    }
}

@Composable
fun RadioButtonExample() {
    val myBackgroundColor = remember {
        mutableStateOf(Color.White)
    }
    val radioIndex = remember {
        mutableIntStateOf(0)
    }
    val radioTexts = listOf("Red", "Green", "Yellow", "Gray")
    val colorList = arrayListOf(Color.Red, Color.Green, Color.Yellow, Color.Gray)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = myBackgroundColor.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(75.dp))

        Column() {
            radioTexts.forEachIndexed { position, name ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(true, onClick = {
                        radioIndex.intValue = position
                    })
                ) {
                    RadioButton(
                        selected = name == radioTexts[radioIndex.intValue],
                        onClick = {
                            radioIndex.intValue = position
                        },
                        colors = RadioButtonDefaults.colors(Color.Blue)
                    )
                    Text(text = name)
                }
            }
        }

        Spacer(modifier = Modifier.size(50.dp))

        Button(
            {
                myBackgroundColor.value = colorList[radioIndex.intValue]
            }
        ) {
            Text(text = "Change Background Color")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun RadioButtonExamplePreview() {
    RadioButtonsTheme {
        RadioButtonExample()
    }
}