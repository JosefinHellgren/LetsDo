package com.example.letsdo.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Categories {
    Feelgood, Should
}

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id:  Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "note") val note: String?,
    @ColumnInfo(name = "category") val category: Categories?,
    @ColumnInfo(name = "review") val review: String?,
    @ColumnInfo(name = "motivated") val motivated: Int?,
    @ColumnInfo(name = "done") val done: Boolean?
): java.io.Serializable
