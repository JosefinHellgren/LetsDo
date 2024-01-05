package com.example.letsdo.todoViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdo.Model.Todo
import com.example.letsdo.Model.TodoDAOInterface
import com.example.letsdo.Model.TodoDatabase
import com.example.letsdo.Model.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): ViewModel() {
    private val todoRepository: TodoRepository
    val allTodo: LiveData<List<Todo>>
    val feelgoodTodos: LiveData<List<Todo>>
    val shouldTodos: LiveData<List<Todo>>
    val doneTodos: LiveData<List<Todo>>
    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        todoRepository = TodoRepository(dao)
        allTodo = todoRepository.allTodos
        feelgoodTodos = todoRepository.feelgoodTodos
        shouldTodos = todoRepository.shouldTodos
        doneTodos = todoRepository.doneTodos
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        todoRepository.insertTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.deleteTodo(todo)
    }

    fun updateTitleAndNote(title: String?, note: String?, id: Int) = viewModelScope.launch(Dispatchers.IO) {

        if (title != null && note != null) {
            todoRepository.updateTitleAndNote(id, note, title)
        } else {
            // Handle the case where either title or note is null
            // You might want to log an error, show a message, or take appropriate action
        }
    }

    fun addReview(review: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.addReview(id,review)
    }

    fun updateDone(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.updateDone(todo.id,todo.done)
    }
}