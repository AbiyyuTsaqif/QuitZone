package com.example.quitzone.ui.questionare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.R
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.desctext
import com.example.quitzone.viewmodel.profilingViewModel.GenderViewModel

@Composable
fun GenderPage(navController: NavController) {
    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)
    val genderViewModel: GenderViewModel = viewModel()

    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                StepText(name = "STEP 2/10")
                Spacer(modifier = Modifier.height(72.dp))
                QuestionText(name = "Which one are you?")
                Spacer(modifier = Modifier.height(25.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    item {
                        GenderOption(
                            gender = "Male",
                            isSelected = genderViewModel.selectedGender == "Male",
                            onSelect = {
                                genderViewModel.selectGender("Male")
                            }
                        )
                    }
                    item {
                        GenderOption(
                            gender = "Female",
                            isSelected = genderViewModel.selectedGender == "Female",
                            onSelect = {
                                genderViewModel.selectGender("Female")
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                DescText(
                    name = "To give you a customized experience\n" +
                            "we need to know your gender"
                )
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                BoxButton(
                    text = "Previous",
                    backgroundColor = Putih,
                    textColor = Color.Black
                ) {
                    navController.navigate("agepage")
                    println("Previous button clicked!")
                }
                Spacer(modifier = Modifier.width(10.dp))
                BoxButton(
                    text = "Next",
                    backgroundColor = Ungu,
                    textColor = Putih
                ) {
                    navController.navigate("smokinghabits")
                    sharedpreferences.setGender(genderViewModel.selectedGender.toString())
                    println("Next button clicked! Selected gender: ${genderViewModel.selectedGender}")
                }
            }
        }
    }
}

@Composable
fun GenderOption(gender: String, isSelected: Boolean, onSelect: () -> Unit) {
    val backgroundColor = if (isSelected) Ungu else Putih
    val imageResource = if (gender == "Male") R.drawable.man else R.drawable.women

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BoxGender(
            backgroundColor = backgroundColor,
            imageResource = imageResource,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.height(3.dp))
        QuestionText(name = if (gender == "Male") "Man" else "Woman")
    }
}

@Composable
fun StepText(name: String) {
    Text(
        text = name,
        style = TextStyle(
            color = Ungu,
            fontSize = 15.sp,
        )
    )
}

@Composable
fun QuestionText(name: String) {
    Text(
        text = name,
        fontSize = 18.sp,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun DescText(name: String) {
    Text(
        text = name,
        fontSize = 14.sp,
        color = desctext,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BoxGender(
    backgroundColor: Color,
    imageResource: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 202.dp)
            .background(backgroundColor, shape = RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BoxButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(width = 166.dp, height = 49.dp)
            .background(backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = textColor
        )
    }
}






