package com.richdev.radiobuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.richdev.radiobuttons.ui.theme.RadioButtonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioButtonsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // RadioButtonExample()
                    // SwitchExample()
                }
            }
        }
    }
}

@Composable
fun DropdownExample() {
    val selectedItem = remember {
        mutableStateOf("Germany")
    }
    val dropDownStatus = remember {
        mutableStateOf(false)
    }
    // val countryList = listOf("Germany", "England", "Italy", "Belgium", "Turkey", "Bulgaria", "Netherlands", "Russia", "Brazil", "Argentina", "Germany", "England", "Italy", "Belgium", "Turkey", "Bulgaria", "Netherlands", "Russia", "Brazil", "Argentina")
    val countryList = stringArrayResource(id = R.array.countryList)
    val itemPosition = remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(enabled = true, onClick = {
                    dropDownStatus.value = true
                })
            ) {
                Text(
                    text = countryList[itemPosition.intValue],
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        dropDownStatus.value = true
                    })
                )
                Image(painter = painterResource(
                    id = R.drawable.drop_down_icon),
                    contentDescription = ""
                )
            }

            DropdownMenu(
                expanded = dropDownStatus.value,
                onDismissRequest = {
                    dropDownStatus.value = false
                }
            ) {
                countryList.forEachIndexed { index, country ->
                    DropdownMenuItem(
                        onClick = {
                            dropDownStatus.value = false
                            itemPosition.intValue = index
                        },
                        text = {
                            Text(text = country)
                        }
                    )
                }
//                DropdownMenuItem(
//                    onClick = {
//                        dropDownStatus.value = false
//                        selectedItem.value = "England"
//                    },
//                    text = {
//                        Text(text = "England")
//                    }
//                )
//                DropdownMenuItem(
//                    onClick = {
//                        dropDownStatus.value = false
//                        selectedItem.value = "Italy"
//                    },
//                    text = {
//                        Text(text = "Italy")
//                    }
//                )
            }
        }
    }
}

@Composable
fun SwitchExample() {
    val switchState = remember {
        mutableStateOf(false)
    }
    val myText = remember {
        mutableStateOf("The images is visible")
    }
    val myAlphaValue = remember {
        mutableFloatStateOf(1F)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(50.dp))

        Switch(
            checked = switchState.value,
            onCheckedChange = {
                switchState.value = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                checkedTrackColor = Color.Red,
                uncheckedThumbColor = Color.Red,
                uncheckedTrackColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.size(30.dp))

        if (!switchState.value) {
            myText.value = "The image is visible"
            myAlphaValue.floatValue = 1F
        } else {
            myText.value = "The image is not visible"
            myAlphaValue.floatValue = 0F
        }

        Image(
            painter = painterResource(id = R.drawable.first_image),
            contentDescription = "",
            modifier = Modifier.size(300.dp).alpha(myAlphaValue.floatValue),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = myText.value,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Blue)
                .width(300.dp)
                .padding(top = 10.dp, bottom= 10.dp)
        )
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
        DropdownExample()
    }
}