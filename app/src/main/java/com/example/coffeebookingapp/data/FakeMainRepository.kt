package com.example.coffeebookingapp.data

import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.Redeemable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeMainRepository: MainRepository {
    private val products = listOf("Americano", "Cappuccino", "Mocha", "Flat White")

    private val fullName = MutableStateFlow("Anderson")
    private val stampCount = MutableStateFlow(0)

    private val checkedOutOrders = MutableStateFlow(listOf(
        Order("1", "Cappuccino", "2021-10-01 10:00", 20.0, "Jl. Raya Kebayoran Lama No. 12"),
        Order("2", "Cappuccino", "2021-10-02 10:00", 21.0, "Jl. Raya Kebayoran Lama No. 13"),
        Order("3", "Cappuccino", "2021-10-03 10:00", 22.0, "Jl. Raya Kebayoran Lama No. 14"),
        Order("4", "Cappuccino", "2021-10-04 10:00", 23.0, "Jl. Raya Kebayoran Lama No. 15"),
        Order("5", "Cappuccino", "2021-10-05 10:00", 24.0, "Jl. Raya Kebayoran Lama No. 16"),
    ))
    private val ongoingOrders = MutableStateFlow(checkedOutOrders.value.toSet())
    private val historyOrders = MutableStateFlow(emptySet<Order>())

    override fun observeFullName(): Flow<String> {
        return fullName
    }

    override fun changeFullName(fullName: String) {
        TODO("Not yet implemented")
    }

    override fun observePhone(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun changePhone(phone: String) {
        TODO("Not yet implemented")
    }

    override fun observeEmail(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun changeEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun observeAddress(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun changeAddress(address: String) {
        TODO("Not yet implemented")
    }

    override fun getProducts(): List<String> {
        return products
    }

    override fun getPrice(product: String, option: ProductOption): Int {
        return 10 * option.quantity
    }

    override fun observeCart(): Flow<List<CartItem>> {
        TODO("Not yet implemented")
    }

    override fun addToCart(cartItem: CartItem) {
        TODO("Not yet implemented")
    }

    override fun removeFromCart(itemId: String) {
        TODO("Not yet implemented")
    }

    override fun observeStampCount(): Flow<Int> {
        return stampCount
    }

    override fun resetStampCount() {
        stampCount.update { 0 }
    }

    override fun observePoints(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun observeRedeemableProducts(): Flow<List<Redeemable>> {
        TODO("Not yet implemented")
    }

    override fun redeemProduct(redeemableId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun observePointsHistory(): Flow<List<PointReward>> {
        TODO("Not yet implemented")
    }

    override fun observeCheckedOutOrders(): Flow<List<Order>> {
        return checkedOutOrders
    }

    override fun checkOutOrder(order: Order) {
        TODO("Not yet implemented")
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