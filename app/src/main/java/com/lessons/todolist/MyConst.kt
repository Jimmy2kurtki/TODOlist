package com.lessons.todolist

const val INTENT_KEY = "item"

const val ITEMS_WAITING = "itemsWaiting"
const val NAME_WAITING = "nameWaiting"
const val PRICE_WAITING = "priceWaiting"
const val CREATE_WAITING =
    "CREATE TABLE $ITEMS_WAITING (id INTEGER PRIMARY KEY, $NAME_WAITING TEXT, $PRICE_WAITING INTEGER)"

const val ITEMS_READY = "itemsReady"
const val NAME_READY = "nameReady"
const val PRICE_READY = "priceReady"
const val CREATE_READY =
    "CREATE TABLE $ITEMS_READY (id INTEGER PRIMARY KEY, $NAME_READY TEXT, $PRICE_READY INTEGER)"
