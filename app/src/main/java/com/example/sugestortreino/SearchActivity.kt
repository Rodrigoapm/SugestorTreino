package com.example.sugestortreino

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.sugestortreino.R
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
    // Adicionando habilidades ao Spinner
    val skills = arrayOf("Remate", "Passe", "Finta", "Defesa", "Remate")
    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, skills)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSkill.adapter = adapter

        btnSuggest.setOnClickListener {
            val skillSelected = spinnerSkill.selectedItem.toString()
            val suggestion = getSuggestion(skillSelected)
            tvSuggestion.text = suggestion
        }

        btnStartTimer.setOnClickListener {
            startTimer(tvTimer)
        }
    }

    private fun getSuggestion(skillInput: String): String {
        return when (skillInput.lowercase(Locale.getDefault())) {
            "remate" -> "Treino de Remate:\n- 3 séries de 10 remates com cada pé.\n- 2 séries de 10 remates com a bola em movimento."
            "passe" -> "Treino de Passe:\n- 3 séries de 20 passes curtos.\n- 2 séries de 20 passes longos."
            "finta" -> "Treino de finta:\n- 3 séries de 10 fintas entre cones.\n- 2 séries de fintas com mudança de direção."
            "defesa" -> "Treino de Defesa:\n- 3 séries de 10 desarmes.\n- 2 séries de 10 interceptações."
            "Remate" -> "Treino de Remate:\n- 3 séries de 5 remates na area dos 5 metros. \n- 3 series de 5 remates cruzados.\n- 2 series de remates so com 1 toque na bola."
            else -> "Desculpe, não temos um treino específico para essa habilidade."
        }
    }
    private fun startTimer(tvTimer: TextView) {
        val timer = object : CountDownTimer(60000, 1000) { // 60 segundos
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                tvTimer.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
            }

            override fun onFinish() {
                tvTimer.text = "00:00"
            }
        }
        timer.start()
    }
}
