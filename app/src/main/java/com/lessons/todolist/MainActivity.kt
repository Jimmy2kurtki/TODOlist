package com.lessons.todolist

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.viewbinding.ViewBinding
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemsAdapter.Listener{

    val dbHelper = DbHelper(this, null)

    var listItemsWaiting: MutableList<Item> = mutableListOf()
    var listItemsReady: MutableList<Item> = mutableListOf()
    val adapter = ItemsAdapter(listItemsWaiting,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerItems: RecyclerView = findViewById(R.id.todo_list)
        val buttonAdd: TextView = findViewById(R.id.button_add)

        listItemsWaiting.addAll(dbHelper.getItemsWaiting())
        listItemsReady.addAll(dbHelper.getItemsReady())

        buttonAdd.setOnClickListener(){
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        addInListWaiting()

        recyclerItems.layoutManager = LinearLayoutManager(this)
        recyclerItems.adapter = adapter

        val buttonDone = recyclerItems.findViewById<TextView>(R.id.item_list_button_done)
        buttonDone?.setOnClickListener{

        }
    }

    fun addInListWaiting(){
        val item = intent.getStringExtra(INTENT_KEY)
        if (item != null) {
            listItemsWaiting.add(Item(item))
            dbHelper.addItemWaiting(Item(item))
        }
    }

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    fun dialogPrice(view: View){
        val position: Int = view.toString().toInt()
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_price,null)
        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(false)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

        val buttonPriceOk = dialogBinding.findViewById<TextView>(R.id.button_price_ok)
        val buttonPriceCancel = dialogBinding.findViewById<TextView>(R.id.button_price_cancel)
        val editTextPrice = dialogBinding.findViewById<EditText>(R.id.editText_price)

        buttonPriceOk.setOnClickListener{
            listItemsWaiting[position].setPrice(editTextPrice.toString().toInt())

            dbHelper.addItemReady(listItemsWaiting[position])
            listItemsReady.add(listItemsWaiting[position])

            dbHelper.deleteOneWaiting(listItemsWaiting[position])
            listItemsWaiting.removeAt(position)

            adapter.notifyDataSetChanged()
        }

        buttonPriceCancel.setOnClickListener{
            myDialog.dismiss()
        }
    }

    override fun onClick(item: Item) {

    }

}

