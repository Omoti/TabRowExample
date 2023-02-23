package com.omoti.tabsexample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omoti.tabsexample.ui.theme.TabsExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onFixedClick: (Int) -> Unit,
    onScrollableClick: (Int) -> Unit,
) {
    var input by remember { mutableStateOf("0") }
    val initialTabIndex: Int = input.toIntOrNull() ?: 0

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { onFixedClick(initialTabIndex) }) {
            Text(text = "Fixed Tabs")
        }

        Button(onClick = { onScrollableClick(initialTabIndex) }) {
            Text(text = "Scrollable Tabs")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Tab Index: ")
            TextField(
                value = input,
                onValueChange = { input = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(48.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    TabsExampleTheme {
        MainScreen(onFixedClick = {}, onScrollableClick = {})
    }
}
