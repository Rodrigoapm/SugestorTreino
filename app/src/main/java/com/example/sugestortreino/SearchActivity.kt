package com.seu_nome.futsaltrainer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sugestortreino.R
import java.util.Locale

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val etSkill: EditText = findViewById(R.id.et_skill)
        val tvSuggestion: TextView = findViewById(R.id.tv_suggestion)
        val btnSuggest: Button = findViewById(R.id.btn_suggest)

        btnSuggest.setOnClickListener {
            val skillInput = etSkill.text.toString().trim()
            val suggestion = getSuggestion(skillInput)
            tvSuggestion.text = suggestion
        }
    }

    private fun getSuggestion(skillInput: String): String {
        return when (skillInput.lowercase(Locale.getDefault())) {
            "remate" -> "Treino de Remate:\n- 3 séries de 10 remates com cada pé.\n- 2 séries de 10 remates com a bola em movimento."
            "passe" -> "Treino de Passe:\n- 3 séries de 20 passes curtos.\n- 2 séries de 20 passes longos."
            "drible" -> "Treino de Drible:\n- 3 séries de 10 dribles entre cones.\n- 2 séries de dribles com mudança de direção."
            "defesa" -> "Treino de Defesa:\n- 3 séries de 10 desarmes.\n- 2 séries de 10 interceptações."
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
