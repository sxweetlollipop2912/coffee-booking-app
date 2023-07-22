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

class FakeMainRepository : MainRepository {
    private val products = listOf("Americano", "Cappuccino", "Mocha", "Flat White")

    private val fullName = MutableStateFlow("Anderson")
    private val phone = MutableStateFlow("+60134589525")
    private val email = MutableStateFlow("anderson@email.com")
    private val address = MutableStateFlow("3 Addersion Court Chino Hills, HO56824, United States")

    private val stampCount = MutableStateFlow(4)
    private val points = MutableStateFlow(2750)

    private val cart = MutableStateFlow(
        listOf(
            CartItem(
                "1",
                "Americano",
                1.0,
                ProductOption(
                    1,
                    ShotType.SINGLE,
                    TemperatureType.ICED,
                    SizeType.MEDIUM,
                    IceType.FULL
                )
            ),
            CartItem(
                "2",
                "Cappuccino",
                1.0,
                ProductOption(
                    1,
                    ShotType.SINGLE,
                    TemperatureType.HOT,
                    SizeType.MEDIUM,
                    IceType.FULL
                )
            ),
            CartItem(
                "3",
                "Flat White",
                1.25,
                ProductOption(
                    1,
                    ShotType.DOUBLE,
                    TemperatureType.HOT,
                    SizeType.MEDIUM,
                    IceType.FULL
                )
            ),
            CartItem(
                "4",
                "Mocha",
                1.5,
                ProductOption(1, ShotType.SINGLE, TemperatureType.HOT, SizeType.LARGE, IceType.FULL)
            ),
            CartItem(
                "5",
                "Americano",
                2.0,
                ProductOption(
                    2,
                    ShotType.SINGLE,
                    TemperatureType.ICED,
                    SizeType.MEDIUM,
                    IceType.FULL
                )
            ),
        )
    )

    private val redeemables = MutableStateFlow(
        listOf(
            Redeemable("1", "Americano", "04.07.21", 1340),
            Redeemable("2", "Flat White", "04.07.21", 1340),
            Redeemable("3", "Cappuccino", "04.07.21", 1340),
        )
    )

    private val pointsHistory = MutableStateFlow(
        listOf(
            PointReward(
                id = "1",
                product = "Americano",
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = "2",
                product = "Cappuccino",
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = "3",
                product = "Flat White",
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = "4",
                product = "Americano",
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
            PointReward(
                id = "5",
                product = "Mocha",
                points = 12,
                datetime = "24 June | 12:30 PM"
            ),
        )
    )

    private val checkedOutOrders = MutableStateFlow(
        listOf(
            Order("1", "Cappuccino", "2021-10-01 10:00", 20.0, "Jl. Raya Kebayoran Lama No. 12"),
            Order("2", "Cappuccino", "2021-10-02 10:00", 21.0, "Jl. Raya Kebayoran Lama No. 13"),
            Order("3", "Cappuccino", "2021-10-03 10:00", 22.0, "Jl. Raya Kebayoran Lama No. 14"),
            Order("4", "Cappuccino", "2021-10-04 10:00", 23.0, "Jl. Raya Kebayoran Lama No. 15"),
            Order("5", "Cappuccino", "2021-10-05 10:00", 24.0, "Jl. Raya Kebayoran Lama No. 16"),
        )
    )
    private val ongoingOrders = MutableStateFlow(checkedOutOrders.value.toSet())
    private val historyOrders = MutableStateFlow(emptySet<Order>())

    override fun observeFullName(): Flow<String> {
        return fullName
    }

    override fun changeFullName(upd: String) {
        fullName.update { upd }
    }

    override fun observePhone(): Flow<String> {
        return phone
    }

    override fun changePhone(upd: String): Boolean {
        if (upd.length != 12 || upd[0] != '+') return false
        phone.update { upd }
        return true
    }

    override fun observeEmail(): Flow<String> {
        return email
    }

    override fun changeEmail(upd: String): Boolean {
        if (!upd.contains('@')) return false
        email.update { upd }
        return true
    }

    override fun observeAddress(): Flow<String> {
        return address
    }

    override fun changeAddress(upd: String) {
        address.update { upd }
    }

    override fun getProducts(): List<String> {
        return products
    }

    override fun getPrice(product: String, option: ProductOption): Double {
        var price = option.quantity.toDouble()
        if (option.size == SizeType.LARGE) price += 0.55
        if (option.size == SizeType.SMALL) price -= 0.55
        if (option.shot == ShotType.DOUBLE) price += 0.25
        return price
    }

    override fun observeCart(): Flow<List<CartItem>> {
        return cart
    }

    override fun addToCart(cartItem: CartItem) {
        cart.update { it + cartItem }
    }

    override fun removeFromCart(itemId: String) {
        cart.update { it.filter { item -> item.id != itemId } }
    }

    override fun observeStampCount(): Flow<Int> {
        return stampCount
    }

    override fun resetStampCount() {
        stampCount.update { 0 }
    }

    override fun observePoints(): Flow<Int> {
        return points
    }

    override fun observeRedeemableProducts(): Flow<List<Redeemable>> {
        return redeemables
    }

    override fun redeemProduct(redeemableId: String): Boolean {
        val redeemable = redeemables.value.find { it.id == redeemableId } ?: return false
        if (points.value < redeemable.pointsRequired) return false
        redeemables.update { it - redeemable }
        points.update { it - redeemable.pointsRequired }
        return true
    }

    override fun observePointsHistory(): Flow<List<PointReward>> {
        return pointsHistory
    }

    override fun observeCheckedOutOrders(): Flow<List<Order>> {
        return checkedOutOrders
    }

    override fun checkOut(): Boolean {
        if (cart.value.isEmpty()) return false
        val orders = mutableListOf<Order>()
        var totalPrice = 0.0
        for(item in cart.value) {
            val order = Order(
                id = (checkedOutOrders.value.last().id.toInt() + 1).toString(),
                product = item.product,
                datetime = "23 July | 01:06 AM",
                price = item.price,
                address = address.value
            )
            orders.add(order)
            totalPrice += item.price
        }
        cart.update { emptyList() }
        checkedOutOrders.update { it + orders }
        ongoingOrders.update { it + orders }
        stampCount.update { min(it + orders.size, 8) }
        points.update { it + (totalPrice * 10).toInt() }
        return true
    }

    override fun moveToHistory(orderID: String): Boolean {
        val order = checkedOutOrders.value.find { it.id == orderID } ?: return false
        ongoingOrders.update { it - order }
        historyOrders.update { it + order }
        return true
    }

    override fun moveToOngoing(orderID: String): Boolean {
        val order = checkedOutOrders.value.find { it.id == orderID } ?: return false
        historyOrders.update { it - order }
        ongoingOrders.update { it + order }
        return true
    }

    override fun observeOngoingOrders(): Flow<Set<Order>> {
        return ongoingOrders
    }

    override fun observeHistoryOrders(): Flow<Set<Order>> {
        return historyOrders
    }
}