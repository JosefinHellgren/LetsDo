package com.example.letsdo.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.letsdo.Model.Categories
import com.example.letsdo.Model.Todo
import com.example.letsdo.todoViewModel.TodoViewModel


@Composable
fun AddTodoScreen(todoViewModel: TodoViewModel){
    var titleInputText by rememberSaveable { mutableStateOf("") }
    var noteInputText by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf(Categories.Feelgood) }

    Column(modifier = Modifier
        .padding(16.dp, 16.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Add something to do",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = titleInputText,
            onValueChange = {newtext -> titleInputText = newtext},
            placeholder = { Text(text = "Title") }
            )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = noteInputText,
            onValueChange = {newtext -> noteInputText = newtext},
            placeholder = { Text(text = "Note") }
        )
        categoryDropDownMenu(category = category){
                selectedCategory ->
            category = selectedCategory
        }
       Button(modifier = Modifier
           .height(56.dp)
           .fillMaxWidth()
           .align(Alignment.CenterHorizontally)
           ,onClick = { todoViewModel.insertTodo(
               Todo(title = titleInputText,
                   note = noteInputText,
                   category = category,
                   done= false,
                   motivated = 5,
                   review = null
                   )
           ) }) {
Text(text = "Add")
       }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun categoryDropDownMenu(category: Categories,onCategorySelected: (Categories) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Category",
            style = MaterialTheme.typography.bodyLarge
        )
        val options = Categories.entries
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0].name) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            )
            {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            selectedOptionText = selectionOption.name
                            onCategorySelected(selectionOption)
                            expanded = false
                        }
                    )
                }

            }


        }
    }
}