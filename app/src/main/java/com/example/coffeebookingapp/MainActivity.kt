package com.example.coffeebookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeebookingapp.data.FakeMainRepository
import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.model.IceType
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.Redeemable
import com.example.coffeebookingapp.model.ShotType
import com.example.coffeebookingapp.model.SizeType
import com.example.coffeebookingapp.model.TemperatureType
import com.example.coffeebookingapp.ui.components.CartItemCard
import com.example.coffeebookingapp.ui.components.OrderSlot
import com.example.coffeebookingapp.ui.components.PointCard
import com.example.coffeebookingapp.ui.components.RedeemableSlot
import com.example.coffeebookingapp.ui.components.RewardHistorySlot
import com.example.coffeebookingapp.ui.components.StampCountCard
import com.example.coffeebookingapp.ui.details.DetailsViewModel
import com.example.coffeebookingapp.ui.home.HomeViewModel
import com.example.coffeebookingapp.ui.my_order.MyOrderViewModel
import com.example.coffeebookingapp.ui.order_success.OrderSuccessScreen
import com.example.coffeebookingapp.ui.theme.CoffeeBookingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myOrderViewModel: MyOrderViewModel = viewModel(
                factory = MyOrderViewModel.provideFactory(
                    repository = FakeMainRepository()
                )
            )
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    repository = FakeMainRepository()
                )
            )
            val detailsViewModel: DetailsViewModel = viewModel(
                factory = DetailsViewModel.provideFactory(
                    repository = FakeMainRepository(),
                    product = "Americano"
                )
            )
            CoffeeBookingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OrderSuccessScreen()
//                    Column(
//                        modifier = Modifier.padding(20.dp)
//                    ) {
////                        MyOrderRoute(myOrderViewModel = myOrderViewModel)
////                        HomeRoute(homeViewModel = homeViewModel)
////                        DetailsRoute(detailsViewModel = detailsViewModel)
//                        StampCountCard(stampCount = 7)
//
//                        val cartItem = CartItem(
//                            id = "1",
//                            product = "Mocha",
//                            price = 100.0,
//                            option = ProductOption(
//                                quantity = 3,
//                                shot = ShotType.SINGLE,
//                                temperature = TemperatureType.ICED,
//                                size = SizeType.MEDIUM,
//                                ice = IceType.FULL
//                            )
//                        )
//                        CartItemCard(item = cartItem)
//                        PointCard(points = 2750)
//                        RewardHistorySlot(reward = PointReward(
//                            id = "1",
//                            product = "Americano",
//                            points = 12,
//                            datetime = "24 June | 12:30 PM"
//                        ))
//                        OrderSlot(order = Order(
//                            id = "1",
//                            product = "Americano",
//                            datetime = "24 June | 12:30 PM",
//                            price = 3.0,
//                            address = "3 Addersion Court Chino Hills, HO56824, United States",
//                        ))
//                        RedeemableSlot(redeemable = Redeemable(
//                            id = "1",
//                            product = "Americano",
//                            pointsRequired = 1340,
//                            validUntil = "04.07.21"
//                        ))
//                        RedeemableSlot(redeemable = Redeemable(
//                            id = "1",
//                            product = "Flat White",
//                            pointsRequired = 1340,
//                            validUntil = "04.07.21"
//                        ))
//                    }
                }
            }
        }
    }
}
@Composable
fun MyOrderRoute(
    myOrderViewModel: MyOrderViewModel,
) {
    Row {
        Column {
            val ongoing: State<Set<Order>> = myOrderViewModel.ongoing.collectAsStateWithLifecycle()
            for(order in ongoing.value) {
                Button(onClick = { myOrderViewModel.moveToHistory(order.id) }) {
                    Text(text = order.id)
                }
            }
        }
        Column {
            val history: State<Set<Order>> = myOrderViewModel.history.collectAsStateWithLifecycle()
            for(order in history.value) {
                Button(onClick = { myOrderViewModel.moveToOngoing(order.id) }) {
                    Text(text = order.id)
                }
            }
        }
    }
}
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel
) {
    Column {
        val fullName: State<String> = homeViewModel.fullName.collectAsStateWithLifecycle()
        val stampCount: State<Int> = homeViewModel.stampCount.collectAsStateWithLifecycle()
        val products: List<String> = homeViewModel.products
        Text(text = fullName.value)
        Text(text = stampCount.value.toString())
        for(product in products) {
            Button(onClick = {  }) {
                Text(text = product)
            }
        }
    }
}

@Composable
fun DetailsRoute(
    detailsViewModel: DetailsViewModel,
) {
    Column {
        val option: State<ProductOption> = detailsViewModel.option.collectAsStateWithLifecycle()
        val totalPrice: State<Int> = detailsViewModel.totalPrice.collectAsStateWithLifecycle()

        Text(text = "Buying: ${detailsViewModel.product}")
        Text(text = "Quantity: ${option.value.quantity}")
        Text(text = "Shot: ${option.value.shot}")
        Text(text = "Temperature: ${option.value.temperature}")
        Text(text = "Size: ${option.value.size}")
        Text(text = "Ice: ${option.value.ice}")
        Text(text = "Price: ${totalPrice.value}")

        Button(onClick = { detailsViewModel.incQuantity() }) {
            Text(text = "Inc quantity")
        }
        Button(onClick = { detailsViewModel.decQuantity() }) {
            Text(text = "Dec quantity")
        }
        // A box filled with color
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

