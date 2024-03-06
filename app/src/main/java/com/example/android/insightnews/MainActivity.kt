package com.example.android.insightnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.android.insightnews.ui.InsightNewsApp
import com.example.android.insightnews.ui.theme.InsightNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsightNewsTheme {
                InsightNewsApp()
            }
        }
    }
}