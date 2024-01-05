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
    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        todoRepository = TodoRepository(dao)
        allTodo = todoRepository.allTodos
        feelgoodTodos = todoRepository.feelgoodTodos
        shouldTodos = todoRepository.shouldTodos
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        todoRepository.insertTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.deleteTodo(todo)
    }

    fun updateTitleAndNote(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.updateTitleAndNote(todo.title, todo.note, todo.id)
    }

    fun addReview(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.addReview(todo.id,todo.review)
    }
}