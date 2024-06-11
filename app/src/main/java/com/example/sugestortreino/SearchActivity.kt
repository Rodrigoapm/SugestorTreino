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
        val skill = skillInput.lowercase(Locale.getDefault())

        return when {
            skill.contains("remate") -> "Treino de Remate:\n- 3 séries de 10 remates com cada pé.\n- 2 séries de 10 remates com a bola em movimento."
            skill.contains("passe") -> "Treino de Passe:\n- 3 séries de 20 passes curtos.\n- 2 séries de 20 passes longos."
            skill.contains("drible") -> "Treino de Drible:\n- 3 séries de 10 dribles entre cones.\n- 2 séries de dribles com mudança de direção."
            skill.contains("defesa") -> "Treino de Defesa:\n- 3 séries de 10 desarmes.\n- 2 séries de 10 interceptações."
            else -> "Desculpe, não temos um treino específico para essa habilidade."
        }
    }
}
