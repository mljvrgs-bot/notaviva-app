package com.example.notaviva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.notaviva.ui.NotaVivaNavHost
import com.example.notaviva.ui.theme.NotaVivaTheme
import com.example.notaviva.viewmodel.CaseViewModel
import com.example.notaviva.viewmodel.CaseViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: CaseViewModel by viewModels {
        CaseViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotaVivaTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NotaVivaNavHost(viewModel = viewModel)
                }
            }
        }
    }
}
