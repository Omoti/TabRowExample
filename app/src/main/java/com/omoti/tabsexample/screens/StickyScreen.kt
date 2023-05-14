package com.omoti.tabsexample.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import com.omoti.tabsexample.ui.theme.TabsExampleTheme
import kotlinx.coroutines.launch

private const val TOP_CONTENT_HEIGHT = 200

/**
 * FixedTabRow in Sticky Header
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StickyScreen(onBack: () -> Unit, initialTabIndex: Int = 0) {
    val density = LocalDensity.current

    val pagerState = rememberPagerState(initialPage = initialTabIndex)
    val coroutineScope = rememberCoroutineScope()
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    val lazyListStates = titles.map { rememberLazyListState() }
    var topContentHeight by remember { mutableStateOf(TOP_CONTENT_HEIGHT.dp) }
    val showIcons by remember { derivedStateOf { topContentHeight > 0.dp } }

    // 全体のスクロール
    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            // itemsの先頭が見えたらスクロール
            if (available.y > 0) {
                if (lazyListStates[pagerState.currentPage].firstVisibleItemIndex > 0 || lazyListStates[pagerState.currentPage].firstVisibleItemScrollOffset > 0) {
                    return Offset.Zero
                }
            }

            // スクロール量と同じだけTopContentの高さを変える
            topContentHeight = with(density) {
                (topContentHeight + available.y.toDp()).coerceIn(
                    0.dp,
                    TOP_CONTENT_HEIGHT.dp,
                )
            }

            return Offset.Zero
        }
    }

    // itemsのスクロール
    val itemsScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            // TopContentが残っている間はスクロールさせない
            if (available.y < 0 && topContentHeight > 0.dp) {
                return available // すべて消費
            }

            return super.onPreScroll(available, source)
        }
    }

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Sticky TabRow") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                )
            },
            modifier = Modifier.nestedScroll(nestedScrollConnection),
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                TopContent(height = topContentHeight)
                TabRow(selectedTabIndex = pagerState.currentPage) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            icon = if (showIcons) {
                                {
                                    Icon(
                                        imageVector = when (index) {
                                            0 -> Icons.Default.Phone
                                            1 -> Icons.Default.Email
                                            else -> Icons.Default.Person
                                        }, contentDescription = null
                                    )
                                }
                            } else null,
                            text = { Text(text = title, maxLines = 1) },
                        )
                    }
                }
                HorizontalPager(pageCount = titles.size, state = pagerState) { page ->
                    ChildContent(
                        state = lazyListStates[page],
                        modifier = Modifier.nestedScroll(itemsScrollConnection),
                    )
                }
            }
        }
    }
}

@Composable
private fun TopContent(height: Dp) {
    Column(
        modifier = Modifier.height(height),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(TOP_CONTENT_HEIGHT.dp)
                .background(color = Color.LightGray)
                .offset(y = (height - TOP_CONTENT_HEIGHT.dp) / 2),
        ) {
            Text(
                text = "Top Content",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun ChildContent(state: LazyListState, modifier: Modifier = Modifier) {
    LazyColumn(
        state = state,
        modifier = modifier,
    ) {
        items(30) { index ->
            ListItem(headlineText = { Text(text = "item $index") })
        }
    }
}

@Preview
@Composable
fun TopContentPreview() {
    TabsExampleTheme {
        TopContent(TOP_CONTENT_HEIGHT.dp)
    }
}

@Preview
@Composable
private fun StickyScreenPreview() {
    TabsExampleTheme {
        StickyScreen(onBack = {})
    }
}
