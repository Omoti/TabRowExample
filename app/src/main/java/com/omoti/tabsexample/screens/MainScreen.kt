package com.omoti.tabsexample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omoti.tabsexample.ui.theme.TabsExampleTheme

@Composable
fun MainScreen(
    onFixedClick: () -> Unit,
    onScrollableClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = onFixedClick) {
            Text(text = "Fixed Tabs")
        }

        Button(onClick = onScrollableClick) {
            Text(text = "Scrollable Tabs")
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
