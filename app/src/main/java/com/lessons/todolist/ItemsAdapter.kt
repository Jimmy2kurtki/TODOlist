package com.lessons.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(val listener: Listener, var items: MutableList<Item>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    val dbHelper = DbHelper(context, null)

    class ItemHolder(view: View): RecyclerView.ViewHolder(view){

        val binding = Plant
        fun bind (item: Item){

        }

        val tvName: TextView = view.findViewById(R.id.item_list_title_name)
        val tvPrice: TextView = view.findViewById(R.id.item_list_title_price)
        val btnDelete: TextView = view.findViewById(R.id.item_list_button_delete)
        val btnDone: TextView = view.findViewById(R.id.item_list_button_done)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return ItemHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(
        holder: ItemHolder,
        position: Int
    ) {

        holder.tvName.text = items[position].name.toString()
        holder.tvPrice.text = items[position].getPrice().toString() + "₽"
        holder.btnDelete.setOnClickListener(){
            dbHelper.deleteOneWaiting(items[position])
            items.removeAt(position)
            notifyDataSetChanged()
        }
        holder.btnDone.setOnClickListener{

        }


    }

    override fun getItemCount(): Int {
        return items.count()
    }

    interface Listener{
        fun onClick(item: Item)
    }

}