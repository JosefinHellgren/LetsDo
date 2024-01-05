package com.example.letsdo.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Categorys {
    Feelgood, Should
}

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id:  Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "note") val note: String?,
    @ColumnInfo(name = "category") val category: Categorys?,
    @ColumnInfo(name = "review") val review: String?,
    @ColumnInfo(name = "motivated") val motivated: Int?
): java.io.Serializable
