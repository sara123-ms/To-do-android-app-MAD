package com.example.to_doapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var title: String,
    var priority: String
)
