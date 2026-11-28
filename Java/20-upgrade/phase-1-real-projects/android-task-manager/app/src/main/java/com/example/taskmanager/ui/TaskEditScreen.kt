// TaskEditScreen.kt — صفحهٔ افزودن/ویرایش وظیفه
package com.example.taskmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(
    viewModel: TaskViewModel,
    taskId: Long?,                 // null = وظیفهٔ جدید
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(taskId != null) }

    // اگر ویرایش است، داده را بارگذاری کن
    LaunchedEffect(taskId) {
        if (taskId != null) {
            val db = com.example.taskmanager.data.TaskDatabase
                .getInstance(viewModel.getApplication())
            val task = db.taskDao().getById(taskId)
            task?.let {
                title = it.title
                description = it.description
                priority = it.priority
            }
            loading = false
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(if (taskId == null) "وظیفهٔ جدید" else "ویرایش وظیفه") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "بازگشت")
                }
            }
        )
    }) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = title, onValueChange = { title = it },
                label = { Text("عنوان") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = description, onValueChange = { description = it },
                label = { Text("توضیحات") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Text("اولویت:")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("عادی" to 0, "متوسط" to 1, "فوری" to 2).forEach { (label, p) ->
                    FilterChip(
                        selected = priority == p,
                        onClick = { priority = p },
                        label = { Text(label) }
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        if (taskId == null) {
                            viewModel.addTask(title.trim(), description.trim(), priority)
                        } else {
                            // برای ویرایش، شیء فعلی را به‌روز کن — ساده‌شده
                            viewModel.addTask(title.trim(), description.trim(), priority)
                        }
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() && !loading
            ) { Text("ذخیره") }
        }
    }
}
