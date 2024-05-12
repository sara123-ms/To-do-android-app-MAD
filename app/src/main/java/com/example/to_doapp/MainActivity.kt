package com.example.to_doapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// entity - table
// dao - queries
// database

class MainActivity : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java,"To_Do"
        ).build()


        var add = findViewById<Button>(R.id.add)
        var deleteAll = findViewById<Button>(R.id.deleteAll)



        add.setOnClickListener {
            val intent = Intent(this,Create_cardActivity::class.java)
            startActivity(intent)
        }



        deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.taskDao().deleteAllTasks()
            }
            setRecycler()
        }

        setRecycler()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun setRecycler(){
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = Adapter(DataObject.getAllData())
        recyclerView.layoutManager=LinearLayoutManager(this)
    }
}