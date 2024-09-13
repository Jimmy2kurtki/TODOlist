package com.lessons.todolist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val editTextAddName: EditText = findViewById(R.id.editText_add_name)
        val buttonAdd: TextView = findViewById(R.id.textView_add)

        buttonAdd.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            val string: String = editTextAddName.text.toString().toUpperCase().toString()
            intent.putExtra(INTENT_KEY,string)
            startActivity(intent)
        }
    }
}