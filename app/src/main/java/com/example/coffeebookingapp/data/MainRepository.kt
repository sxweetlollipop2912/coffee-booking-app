package com.example.coffeebookingapp.data

import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.Redeemable
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun observeFullName(): Flow<String>
    fun changeFullName(fullName: String)
    fun observePhone(): Flow<String>
    fun changePhone(phone: String)
    fun observeEmail(): Flow<String>
    fun changeEmail(email: String)
    fun observeAddress(): Flow<String>
    fun changeAddress(address: String)

    fun getProducts(): List<String>
    fun getPrice(product: String, option: ProductOption): Int

    fun observeCart(): Flow<List<CartItem>>
    fun addToCart(cartItem: CartItem)
    fun removeFromCart(itemId: String)

    fun observeStampCount(): Flow<Int>
    fun resetStampCount()

    fun observePoints(): Flow<Int>
    fun observeRedeemableProducts(): Flow<List<Redeemable>>
    fun redeemProduct(redeemableId: String): Boolean
    fun observePointsHistory(): Flow<List<PointReward>>

    fun observeCheckedOutOrders(): Flow<List<Order>>
    fun checkOutOrder(order: Order)
    fun moveToHistory(orderID: String): Boolean
    fun moveToOngoing(orderID: String): Boolean
    fun observeOngoingOrders(): Flow<Set<Order>>
    fun observeHistoryOrders(): Flow<Set<Order>>
}