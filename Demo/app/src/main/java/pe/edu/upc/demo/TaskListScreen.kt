package pe.edu.upc.demo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TaskListScreen(modifier: Modifier = Modifier) {


    val tasks = remember {

        mutableStateListOf(
            Task(title = "Asistir a clases", isCompleted = false),
            Task(title = "Ir de compras", isCompleted = false)
        )

    }

    Scaffold { padding ->
        LazyColumn(modifier = modifier.padding(padding)) {
            items(tasks) { task ->
                TaskListItem(task) {
                    tasks.remove(task)
                }
            }
        }
    }

}


@Composable
fun TaskListItem(
    task: Task,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit

) {
    val isCompleted = remember {
        mutableStateOf(false)
    }
    isCompleted.value = task.isCompleted

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = isCompleted.value,
            onCheckedChange = {
                isCompleted.value = !isCompleted.value
                task.isCompleted = isCompleted.value
            }
        )
        Text(
            task.title, modifier = modifier.weight(1f)
        )

        IconButton(

            onClick = {
                onDelete()
            }
        ) {
            Icon(Icons.Default.Delete, contentDescription = null)
        }
    }
}
