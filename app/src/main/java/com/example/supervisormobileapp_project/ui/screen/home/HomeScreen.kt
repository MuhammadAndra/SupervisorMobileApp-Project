package com.example.supervisormobileapp_project.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {

        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}