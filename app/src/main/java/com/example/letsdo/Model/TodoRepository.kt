package com.example.letsdo.Model

import androidx.lifecycle.LiveData

class TodoRepository(
    private val todoDAOInterface: TodoDAOInterface
)
{
    val allTodos: LiveData<List<Todo>> = todoDAOInterface.getAllTodos()
    val feelgoodTodos: LiveData<List<Todo>> = todoDAOInterface.getFeelGoodTodos()
    val shouldTodos: LiveData<List<Todo>> = todoDAOInterface.getShouldTodos()

    suspend fun insertTodo(todo: Todo) {
        todoDAOInterface.insert(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDAOInterface.delete(todo)
    }

    suspend fun updateTitleAndNote(title: String?,note: String?,id: Int?) {
        todoDAOInterface.updateTitleAndNote(id,note,title)
    }

    suspend fun addReview(id: Int? ,review: String?) {
        todoDAOInterface.addReview(id, review)
    }
}