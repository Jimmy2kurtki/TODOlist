package com.lessons.todolist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
            SQLiteOpenHelper(context,"app",factory,1){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_WAITING)
        db?.execSQL(CREATE_READY)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS $ITEMS_WAITING")
        onCreate(db)
        db.execSQL("DROP TABLE IF EXISTS $ITEMS_READY")
        onCreate(db)
    }

    fun addItemReady(item: Item){
        addItemsAll(ITEMS_READY, NAME_READY, PRICE_READY, item)
    }

    fun getItemsReady(): MutableList<Item>{
        return getItemsAll(ITEMS_READY, NAME_READY, PRICE_READY)
    }

    fun addItemWaiting(item: Item){
        addItemsAll(ITEMS_WAITING, NAME_WAITING, PRICE_WAITING, item)
    }

    fun getItemsWaiting(): MutableList<Item>{
        return getItemsAll(ITEMS_WAITING, NAME_WAITING, PRICE_WAITING)
    }

    fun deleteOneWaiting(item: Item){
        val db = this.writableDatabase
        val selection = "$NAME_WAITING LIKE ? AND $PRICE_WAITING LIKE ?"

        val selectionArgs = arrayOf(item.name,item.getPrice().toString())
        db.delete(ITEMS_WAITING, selection, selectionArgs)
        db.close()
    }

    private fun addItemsAll(table: String, name: String, price: String, item: Item){
        val values = ContentValues()
        values.put(name,item.name)
        values.put(price,item.getPrice())

        val db = this.writableDatabase
        db.insert(table,null,values)

        db.close()
    }

    @SuppressLint("Recycle")
    private fun getItemsAll(table: String, name: String, price: String): MutableList<Item>{
        var itemList: MutableList<Item> = mutableListOf()
        val db = this.readableDatabase

        val cursor: Cursor = db.query(table, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val nameId = cursor.getColumnIndex(name)
                val priceId = cursor.getColumnIndex(price)

                val item = Item(cursor.getString(nameId))
                item.setPrice(cursor.getString(priceId).toInt())
                itemList.add(item)

            } while (cursor.moveToNext())
        }

        db.close()
        return itemList
    }

}