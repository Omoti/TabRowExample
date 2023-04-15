package com.omoti.tabsexample.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.omoti.tabsexample.ui.theme.TabsExampleTheme

/**
 * Collapse TopAppBar with FixedTabRow
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapseScreen(onBack: () -> Unit, initialTabIndex: Int = 0) {
    var selectedTabIndex by remember { mutableStateOf(initialTabIndex) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Collapse TabRow") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title, maxLines = 1) },
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(30) { index ->
                    ListItem(headlineText = { Text(text = "item $index") })
                }
            }
        }
    }
}

@Preview
@Composable
private fun CollapseScreenPreview() {
    TabsExampleTheme {
        CollapseScreen(onBack = {})
    }
}
