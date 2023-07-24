package com.example.coffeebookingapp.data

import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.Redeemable
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun observeFullName(): Flow<String>
    fun changeFullName(upd: String)
    fun observePhone(): Flow<String>
    fun changePhone(upd: String): Boolean
    fun observeEmail(): Flow<String>
    fun changeEmail(upd: String): Boolean
    fun observeAddress(): Flow<String>
    fun changeAddress(upd: String)

    fun getProducts(): List<String>
    fun getPrice(product: String, option: ProductOption, isRedeem: Boolean): Double

    fun observeCart(): Flow<List<CartItem>>
    fun getCartItem(itemId: String): CartItem?
    fun addToCart(product: String, option: ProductOption, redeemableId: String?)
    fun modifyCartItem(cardId: String, option: ProductOption): Boolean
    fun removeFromCart(itemId: String): Boolean

    fun observeStampCount(): Flow<Int>
    fun resetStampCount()

    fun observePoints(): Flow<Int>
    fun observeRedeemableProducts(): Flow<List<Redeemable>>
    fun checkIfRedeemable(redeemableId: String): Boolean
    fun redeemProduct(redeemableId: String): Boolean
    fun observePointsHistory(): Flow<List<PointReward>>

    fun observeCheckedOutOrders(): Flow<List<Order>>
    fun checkOut(): Boolean
    fun moveToHistory(orderId: String): Boolean
    fun moveToOngoing(orderId: String): Boolean
    fun observeOngoingOrders(): Flow<List<Order>>
    fun observeHistoryOrders(): Flow<List<Order>>
}