package com.example.coffeebookingapp.ui.redeem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.model.Redeemable
import com.example.coffeebookingapp.ui.components.RedeemableSlot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedeemScreen(
    redeemable: List<Redeemable>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Redeem",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back_arrow),
                            contentDescription = "back",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier.padding(horizontal = 10.dp),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        RedeemScreenContent(
            redeemable,
            screenModifier.padding(30.dp, 10.dp, 30.dp, 0.dp)
        )
    }
}

@Composable
fun RedeemScreenContent(
    redeemable: List<Redeemable>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        content = {
            items(redeemable.size) { index ->
                RedeemableSlot(redeemable = redeemable[index])
            }
        },
        verticalArrangement = Arrangement.spacedBy(50.dp),
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 30.dp)
    )
}