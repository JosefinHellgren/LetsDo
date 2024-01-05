package com.example.letsdo.View

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.letsdo.Model.Todo
import com.example.letsdo.todoViewModel.TodoViewModel
import com.example.letsdo.ui.theme.LetsDoTheme
import java.util.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            LetsDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TodoListScreen()

                    }

                }
            }
        }
    }
}

@Composable
fun TodoListScreen() {
    val context = LocalContext.current
    val todoViewModel = TodoViewModel(context.applicationContext as Application)

    // Observe the allTodo LiveData using observeAsState
    val allTodos by todoViewModel.allTodo.observeAsState(emptyList())

    LazyColumn {
        items(allTodos) { todo ->
            TodoItem(todo = todo)
        }
    }
    AddTodoScreen(todoViewModel)

}
@Composable
fun TodoItem(todo: Todo) {

    Text(text = todo.title ?: "No title available")
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LetsDoTheme {
       TodoListScreen()
    }
}