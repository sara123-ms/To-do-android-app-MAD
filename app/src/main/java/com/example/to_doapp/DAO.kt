package com.example.to_doapp

import androidx.room.*


@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from tasks")
    suspend fun deleteAllTasks()

    @Query("Select * from tasks")
    suspend fun getAllTasks():List<CardInfo>


    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Entity?





}
