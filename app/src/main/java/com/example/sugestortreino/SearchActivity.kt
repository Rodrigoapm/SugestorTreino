@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.sugestortreino

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sugestortreino.ui.theme.SugestorTreinoTheme
import java.util.Locale

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SugestorTreinoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchScreen()
                }
            }
        }
    }
}

@Composable
fun SearchScreen() {
    var selectedSkill by remember { mutableStateOf("Pesquise o seu treino") }
    var suggestion by remember { mutableStateOf("Treino escolhido") }
    var timerText by remember { mutableStateOf("00:00") }
    // Adicionando habilidades ao DropDownMenu
    val skills = arrayOf("Corrida", "Passe", "Finta", "Defesa", "Remate")
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "O que deseja melhorar?",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenu(
            selectedSkill = selectedSkill,
            skills = skills,
            onSkillSelected = { selectedSkill = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            suggestion = getSuggestion(selectedSkill)
        }) {
            Text("Sugerir Treino")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = suggestion,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = timerText,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            startTimer { timerText = it }
        }) {
            Text("Iniciar Treino")
        }
    }
}
@Composable
fun DropdownMenu(selectedSkill: String, skills: Array<String>, onSkillSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedSkill) }

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                label = { Text("Treinos") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                skills.forEach { skill ->
                    DropdownMenuItem(
                        text = { Text(skill) },
                        onClick = {
                            selectedText = skill
                            onSkillSelected(skill)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
private fun getSuggestion(skillInput: String): String {
    return when (skillInput.lowercase(Locale.getDefault())) {
        "corrida" -> "Treino de corrida:\n- 3 voltas ao recinto.\n- 3 séries de 2 sprints."
        "passe" -> "Treino de Passe:\n- 3 séries de 20 passes curtos.\n- 2 séries de 20 passes longos."
        "finta" -> "Treino de finta:\n- 3 séries de 10 fintas entre cones.\n- 2 séries de fintas com mudança de direção."
        "defesa" -> "Treino de Defesa:\n- 3 séries de 10 desarmes.\n- 2 séries de 10 interceptações."
        "Remate" -> "Treino de Remate:\n- 3 séries de 5 remates na area dos 5 metros. \n- 3 series de 5 remates cruzados.\n- 2 series de remates so com 1 toque na bola."
        else -> "Desculpe, não temos um treino específico para essa habilidade."
    }
}
fun startTimer(updateTimerText: (String) -> Unit) {
    val timer = object : CountDownTimer(60000, 1000) { // 60 segundos
        override fun onTick(millisUntilFinished: Long) {
            val seconds = millisUntilFinished / 1000
            updateTimerText(String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60))
        }

        override fun onFinish() {
            updateTimerText("00:00")
        }
    }
    timer.start()
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SugestorTreinoTheme {
        SearchScreen()
    }
}