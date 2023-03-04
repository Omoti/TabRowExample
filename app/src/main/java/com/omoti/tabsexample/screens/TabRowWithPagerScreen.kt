package com.omoti.tabsexample.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.omoti.tabsexample.ui.theme.TabsExampleTheme
import kotlinx.coroutines.launch

/**
 * TabRow with HorizontalPager
 * https://google.github.io/accompanist/pager/
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabRowWithPagerScreen(onBack: () -> Unit, initialTabIndex: Int = 0) {
    val pagerState = rememberPagerState(initialPage = initialTabIndex)
    val coroutineScope = rememberCoroutineScope()
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Fixed TabRow") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = title, maxLines = 1) },
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                count = titles.size,
            ) { page ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "Text tab ${page + 1} selected",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TabRowWithPagerScreenPreview() {
    TabsExampleTheme {
        TabRowWithPagerScreen(onBack = {})
    }
}
