package com.example.letsdo.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TodoDAOInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete()
    suspend fun delete(todo: Todo)

   @Query("SELECT* from todo_table")
   fun getAllTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE category = :feelgoodCategory")
    fun getFeelGoodTodos(feelgoodCategory: Categories = Categories.Feelgood): LiveData<List<Todo>>

    @Query("SELECT * from todo_table WHERE done = true ")
    fun getDoneTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE category = :shouldCategory")
    fun getShouldTodos(shouldCategory: Categories = Categories.Should): LiveData<List<Todo>>

    @Query("UPDATE todo_table set title = :title, note = :note where id = :id")
    suspend fun updateTitleAndNote(id: Int?, title: String?, note: String?)
    @Query("UPDATE todo_table set done = :done WHERE id = :id")
    suspend fun toggleDone(id: Int?, done: Boolean?)
    @Query("UPDATE todo_table set review = :review WHERE id = :id")
    suspend fun addReview(id: Int?, review: String?)



}