package com.example.letsdo.Model

import androidx.lifecycle.LiveData

class TodoRepository(
    private val todoDAOInterface: TodoDAOInterface
)
{
    val allTodos: LiveData<List<Todo>> = todoDAOInterface.getAllTodos()
    val feelgoodTodos: LiveData<List<Todo>> = todoDAOInterface.getFeelGoodTodos()
    val shouldTodos: LiveData<List<Todo>> = todoDAOInterface.getShouldTodos()
    val doneTodos: LiveData<List<Todo>> = todoDAOInterface.getDoneTodos()

    suspend fun insertTodo(todo: Todo) {
        todoDAOInterface.insert(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDAOInterface.delete(todo)
    }

    suspend fun addReview(id: Int? ,review: String?) {
        todoDAOInterface.addReview(id, review)
    }
    suspend fun updateTitleAndNote(id: Int?, title: String?, note: String?) {
        todoDAOInterface.updateTitleAndNote(id, title, note)
    }

    suspend fun updateDone(id: Int?, done: Boolean?) {
        todoDAOInterface.toggleDone(id, done)
    }


}