package com.example.to_doapp


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version=1)
abstract class myDatabase : RoomDatabase(){

    abstract fun taskDao(): DAO


}