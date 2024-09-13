package com.lessons.todolist

import kotlinx.serialization.Serializable

@Serializable
data class Item(val name: String?) {
    private var price = 0
    private var count = 0

    fun setPrice(price: Int){
        this.price = price
    }

    fun getPrice(): Int{
        return price
    }

    fun setCount(count: Int){
        this.count = count
    }

    fun getCount(): Int{
        return count
    }
}