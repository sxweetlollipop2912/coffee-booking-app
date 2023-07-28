package com.example.coffeebookingapp.data

import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.model.IceType
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.Redeemable
import com.example.coffeebookingapp.model.ShotType
import com.example.coffeebookingapp.model.SizeType
import com.example.coffeebookingapp.model.TemperatureType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.lang.Integer.min
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID


class FakeMainRepository : MainRepository {
    private val basePrice = 1.0

    private val products = listOf("Americano", "Cappuccino", "Latte", "Flat White")

    private val fullName = MutableStateFlow("Anderson")
    private val phone = MutableStateFlow("+60134589525")
    private val email = MutableStateFlow("anderson@email.com")
    private val address = MutableStateFlow("3 Addersion Court Chino Hills, HO56824, United States")

    private val stampCount = MutableStateFlow(4)

    private val cart = MutableStateFlow(
        listOf(
            CartItem(
                newUUID(),
                products[0],
                1.0,
                ProductOption(
                    1,
                    ShotType.SINGLE,
                    TemperatureType.ICED,
                    SizeType.MEDIUM,
                    IceType.FULL
                ),
                null,
            ),
            CartItem(
                newUUID(),
                products[1],
                1.0,
                ProductOption(
                    1,
                    ShotType.SINGLE,
                    TemperatureType.HOT,
                    SizeType.MEDIUM,
                    IceType.FULL
                ),
                null,
            ),
            CartItem(
                newUUID(),
                products[2],
                1.25,
                ProductOption(
                    1,
                    ShotType.DOUBLE,
                    TemperatureType.HOT,
                    SizeType.MEDIUM,
                    IceType.FULL
                ),
                null,
            ),
            CartItem(
                newUUID(),
                products[3],
                1.5,
                ProductOption(
                    1,
                    ShotType.SINGLE,
                    TemperatureType.HOT,
                    SizeType.LARGE,
                    IceType.FULL
                ),
                null,
            ),
            CartItem(
                newUUID(),
                products[1],
                2.0,
                ProductOption(
                    2,
                    ShotType.SINGLE,
                    TemperatureType.ICED,
                    SizeType.MEDIUM,
                    IceType.FULL
                ),
                null,
            ),
        )
    )

    private val redeemables = MutableStateFlow(
        listOf(
            Redeemable(newUUID(), products[0], now(false), 50),
            Redeemable(newUUID(), products[1], "04.07.21", 50),
            Redeemable(newUUID(), products[2], "04.07.21", 50),
        )
    )

    private val points = MutableStateFlow(96)
    private val pointsHistory = MutableStateFlow(
        listOf(
            PointReward(
                id = newUUID(),
                product = products[0],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[1],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[2],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[3],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[0],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[0],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[1],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = newUUID(),
                product = products[2],
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
        )
    )

    private val checkedOutOrders = MutableStateFlow(
        listOf(
            Order(newUUID(), products[0], now(true), 3.0, "Jl. Raya Kebayoran Lama No. 12"),
            Order(
                newUUID(),
                products[1],
                "25 June | 12:30 PM",
                4.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
            Order(
                newUUID(),
                products[2],
                "26 June | 12:30 PM",
                5.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
            Order(
                newUUID(),
                products[3],
                "27 June | 12:30 PM",
                6.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
            Order(
                newUUID(),
                products[0],
                "28 June | 12:30 PM",
                7.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
            Order(
                newUUID(),
                products[0],
                "29 June | 12:30 PM",
                8.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
            Order(
                newUUID(),
                products[1],
                "30 June | 12:30 PM",
                9.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
            Order(
                newUUID(),
                products[2],
                "01 July | 12:30 PM",
                10.0,
                "Jl. Raya Kebayoran Lama No. 12"
            ),
        )
    )
    private val ongoingOrders = MutableStateFlow(emptyList<Order>())
    private val historyOrders = MutableStateFlow(checkedOutOrders.value)

    override fun observeFullName(): Flow<String> = fullName

    override fun changeFullName(upd: String) = fullName.update { upd }

    override fun observePhone(): Flow<String> = phone

    override fun changePhone(upd: String): Boolean {
        if (upd.length != 12 || upd[0] != '+') return false
        phone.update { upd }
        return true
    }

    override fun observeEmail(): Flow<String> = email

    override fun changeEmail(upd: String): Boolean {
        if (!upd.contains('@')) return false
        email.update { upd }
        return true
    }

    override fun observeAddress(): Flow<String> = address

    override fun changeAddress(upd: String) = address.update { upd }

    override fun getProducts(): List<String> = products

    override fun getPrice(product: String, option: ProductOption, isRedeem: Boolean): Double {
        var price = basePrice
        if (option.size == SizeType.LARGE) price += 0.50
        if (option.size == SizeType.SMALL) price -= 0.50
        if (option.shot == ShotType.DOUBLE) price += 0.25
        price *= option.quantity
        if (isRedeem) price -= basePrice
        return kotlin.math.max(price, 0.0)
    }

    override fun observeCart(): Flow<List<CartItem>> = cart

    override fun getCartItem(itemId: String): CartItem? = cart.value.find { it.id == itemId }

    override fun addToCart(product: String, option: ProductOption, redeemableId: String?) {
        cart.update {
            it + CartItem(
                id = newUUID(),
                product = product,
                price = getPrice(product, option, redeemableId != null),
                option = option,
                redeemableId = redeemableId
            )
        }
        cart.update {
            it.filter { cartItem -> cartItem.option.quantity > 0 }
        }
    }

    override fun modifyCartItem(cardId: String, option: ProductOption): Boolean {
        var exists = false
        cart.update {
            it.map { cartItem ->
                if (cartItem.id == cardId) {
                    exists = true
                    cartItem.copy(
                        option = option,
                        price = getPrice(cartItem.product, option, cartItem.redeemableId != null)
                    )
                } else {
                    cartItem
                }
            }
        }
        cart.update {
            it.filter { cartItem -> cartItem.option.quantity > 0 }
        }
        return exists
    }

    override fun removeFromCart(itemId: String): Boolean {
        val item = cart.value.find { it.id == itemId } ?: return false
        cart.update { it - item }
        return true
    }

    override fun observeStampCount(): Flow<Int> = stampCount

    override fun resetStampCount() = stampCount.update { 0 }

    override fun observePoints(): Flow<Int> = points

    override fun observeRedeemableProducts(): Flow<List<Redeemable>> = redeemables

    override fun checkIfRedeemable(redeemableId: String): Boolean {
        val redeemable = redeemables.value.find { it.id == redeemableId } ?: return false
        if (points.value < redeemable.pointsRequired) return false
        return true
    }

    override fun redeemProduct(redeemableId: String): Boolean {
        if (!checkIfRedeemable(redeemableId)) return false
        val redeemable = redeemables.value.find { it.id == redeemableId }!!
        redeemables.update { it - redeemable }
        points.update { it - redeemable.pointsRequired }
        return true
    }

    override fun observePointsHistory(): Flow<List<PointReward>> = pointsHistory

    override fun observeCheckedOutOrders(): Flow<List<Order>> = checkedOutOrders

    override fun checkOut(): Boolean {
        if (cart.value.isEmpty()) return false

        val orders = mutableListOf<Order>()
        val ptsHistory = mutableListOf<PointReward>()
        for (item in cart.value) {
            val order = Order(
                id = newUUID(),
                product = item.product,
                datetime = now(true),
                price = item.price,
                address = address.value
            )
            orders.add(order)

            val pts = (item.price * 10).toInt()
            ptsHistory.add(
                PointReward(
                    id = newUUID(),
                    product = item.product,
                    points = pts,
                    datetime = now(true)
                )
            )
            points.update { it + pts }
            if (item.redeemableId != null) {
                val minusPts = redeemables.value.find { it.id == item.redeemableId }?.pointsRequired ?: 0
                points.update { it - minusPts }
            }
        }
        val redeemableIds = cart.value.mapNotNull { it.redeemableId }

        stampCount.update { min(it + cart.value.sumOf { item -> item.option.quantity }, 8) }

        cart.update { emptyList() }
        checkedOutOrders.update { it + orders }
        ongoingOrders.update { it + orders }

        pointsHistory.update { it + ptsHistory }
        pointsHistory.update { it.filter { reward -> reward.points != 0 } }
        redeemables.update { it.filter { redeemable -> redeemable.id !in redeemableIds } }

        return true
    }

    override fun moveToHistory(orderId: String): Boolean {
        val order = checkedOutOrders.value.find { it.id == orderId } ?: return false
        ongoingOrders.update { it - order }
        historyOrders.update { it + order }
        return true
    }

    override fun moveToOngoing(orderId: String): Boolean {
        val order = checkedOutOrders.value.find { it.id == orderId } ?: return false
        historyOrders.update { it - order }
        ongoingOrders.update { it + order }
        return true
    }

    override fun observeOngoingOrders(): Flow<List<Order>> = ongoingOrders

    override fun observeHistoryOrders(): Flow<List<Order>> = historyOrders
}

private fun newUUID(): String = UUID.randomUUID().toString()

private fun now(extended: Boolean): String {
    val now: Date = Calendar.getInstance().time
    val formatter = if (extended) {
        // 24 June | 12:30 PM
        SimpleDateFormat("dd MMMM | hh:mm a", Locale.getDefault())
    } else {
        // 04.07.21
        SimpleDateFormat("dd.MM.yy", Locale.getDefault())
    }
    return formatter.format(now)
}