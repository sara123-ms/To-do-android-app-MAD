package com.example.to_doapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Create_cardActivity : AppCompatActivity() {

    private lateinit var database: myDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_card)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java,"To_Do"
        ).build()



        var save_btn = findViewById<Button>(R.id.save_btn)
        var create_title = findViewById<EditText>(R.id.create_title)
        var create_priority = findViewById<EditText>(R.id.create_priority)


        save_btn.setOnClickListener {
           if(create_title.text.toString().trim{it<=' '}.isNotEmpty()
               && create_priority.text.toString().trim{it<=' '}.isNotEmpty())
           {
               var title=create_title.getText().toString()
               var priority=create_priority.getText().toString()
               DataObject.setData(title,priority)

               GlobalScope.launch {
                   database.taskDao().insertTask(Entity(0,title,priority))
               }





               val intent= Intent(this,MainActivity::class.java)
               startActivity(intent)
           }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}