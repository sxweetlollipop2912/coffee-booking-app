package com.example.coffeebookingapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.CoffeeAvatar
import com.example.coffeebookingapp.ui.components.BottomBar
import com.example.coffeebookingapp.ui.components.BottomBarTab
import com.example.coffeebookingapp.ui.components.StampCountCard
import com.example.coffeebookingapp.ui.navigation.NavRoutes
import com.example.coffeebookingapp.ui.theme.light_darkPrimary
import com.example.coffeebookingapp.ui.theme.light_onBackground2
import com.example.coffeebookingapp.ui.theme.light_onPrimary2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    fullName: String,
    stampCount: Int,
    coffees: List<String>,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    onStampCountClick: () -> Unit,
    onCoffeeClick: (String) -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.Main) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                    ) {
                        Text(
                            text = "Good morning",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = light_onBackground2
                            )
                        )
                        Text(
                            text = fullName,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 18.sp,
                                color = light_darkPrimary,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                )
                            ),
                        )
                    }
                },
                actions = {
                    Row {
                        IconButton(
                            onClick = onCartClick,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.cart),
                                contentDescription = "go to cart",
                                tint = light_darkPrimary
                            )
                        }
                        IconButton(
                            onClick = onProfileClick,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.person),
                                contentDescription = "go to profile",
                                tint = light_darkPrimary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier
                    .padding(10.dp, 15.dp)
            )
        },
        bottomBar = {
            BottomBar(
                tabs = BottomBarTab.values(),
                currentRoute = NavRoutes.Main.HOME,
                navigateToBottomBarRoute = onNavigateToBottomBarRoute
            )
        },
    ) { innerPadding ->
        val screenModifier = Modifier.padding(
            innerPadding.calculateStartPadding(LayoutDirection.Ltr),
            innerPadding.calculateTopPadding(),
            innerPadding.calculateEndPadding(LayoutDirection.Ltr),
            0.dp,
        )
        HomeScreenContent(
            stampCount,
            coffees,
            onStampCountClick,
            onCoffeeClick,
            screenModifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    stampCount: Int,
    coffees: List<String>,
    onStampCountClick: () -> Unit,
    onCoffeeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier
    ) {
        StampCountCard(
            stampCount = stampCount,
            onClick = onStampCountClick,
            modifier = Modifier.padding(25.dp, 0.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(start = 26.dp, end = 26.dp, top = 26.dp, bottom = 20.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Text(
                    text = "Choose your coffee",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = light_onPrimary2
                    ),
                    modifier = Modifier.padding(2.dp, 0.dp, 0.dp, 0.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(bottom = 100.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(coffees.size) { idx ->
                        Card(
                            shape = RoundedCornerShape(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ),
                            onClick = { onCoffeeClick(coffees[idx]) },
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp)
                            ) {
                                CoffeeAvatar(coffee = coffees[idx], width = 120.dp)
                                Text(
                                    text = coffees[idx],
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}