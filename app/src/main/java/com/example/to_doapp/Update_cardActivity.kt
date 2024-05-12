package com.example.to_doapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class Update_cardActivity : AppCompatActivity() {

    private lateinit var database: myDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_card)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java,"To_Do"
        ).build()

        val create_title = findViewById<EditText>(R.id.create_title)
        val create_priority = findViewById<EditText>(R.id.create_priority)
        val deletebtn = findViewById<Button>(R.id.deletebtn)
        val update_btn = findViewById<Button>(R.id.update_btn)


        val pos=intent.getIntExtra("id",-1)
        if(pos!=-1){
            val title=DataObject.getData(pos).title
            val priority=DataObject.getData(pos).priority

            create_title.setText(title)
            create_priority.setText(priority)

            deletebtn.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.taskDao().deleteTask(
                        Entity(pos+1,create_title.text.toString(),create_priority.text.toString())
                    )
                }
                myIntent()
            }


            update_btn.setOnClickListener {
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_priority.text.toString()
                )
                GlobalScope.launch {
                    database.taskDao().updateTask(
                        Entity(
                            pos+1, create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun myIntent(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}