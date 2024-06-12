package com.seu_nome.futsaltrainer

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sugestortreino.R
import java.util.Locale

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val spinnerSkill: Spinner = findViewById(R.id.spinner_skill)
        val tvSuggestion: TextView = findViewById(R.id.tv_suggestion)
        val btnSuggest: Button = findViewById(R.id.btn_suggest)
        val tvTimer: TextView = findViewById(R.id.tv_timer)
        val btnStartTimer: Button = findViewById(R.id.btn_start_timer)

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
