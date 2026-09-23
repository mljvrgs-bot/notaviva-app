package com.example.notaviva

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupTile(R.id.tileNewCase, "📁", getString(R.string.action_new_case_title), getString(R.string.action_new_case_subtitle)) {
            Toast.makeText(this, "Abrir: Nuevo caso", Toast.LENGTH_SHORT).show()
        }

        // ---- Nueva función agregada: Nueva entrevista ----
        setupTile(R.id.tileNewInterview, "🎙️", getString(R.string.action_new_interview_title), getString(R.string.action_new_interview_subtitle)) {
            Toast.makeText(this, "Abrir: Nueva entrevista", Toast.LENGTH_SHORT).show()
        }

        setupTile(R.id.tileMyCases, "🗂️", getString(R.string.action_my_cases_title), getString(R.string.action_my_cases_subtitle)) {
            Toast.makeText(this, "Abrir: Mis casos", Toast.LENGTH_SHORT).show()
        }

        setupTile(R.id.tileStats, "📊", getString(R.string.action_stats_title), getString(R.string.action_stats_subtitle)) {
            Toast.makeText(this, "Abrir: Estadísticas", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.LinearLayout>(R.id.navCases).setOnClickListener {
            Toast.makeText(this, "Ir a: Casos", Toast.LENGTH_SHORT).show()
        }
        findViewById<android.widget.LinearLayout>(R.id.navSettings).setOnClickListener {
            Toast.makeText(this, "Ir a: Configuración", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTile(tileRootId: Int, icon: String, title: String, subtitle: String, onClick: () -> Unit) {
        val tileRoot = findViewById<android.view.View>(tileRootId)
        tileRoot.findViewById<TextView>(R.id.tileIcon).text = icon
        tileRoot.findViewById<TextView>(R.id.tileTitle).text = title
        tileRoot.findViewById<TextView>(R.id.tileSubtitle).text = subtitle
        tileRoot.setOnClickListener { onClick() }
    }
}