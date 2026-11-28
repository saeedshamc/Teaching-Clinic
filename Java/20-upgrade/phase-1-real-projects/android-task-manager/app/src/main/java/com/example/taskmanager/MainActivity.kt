// MainActivity.kt — نقطهٔ ورود + Navigation
package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.taskmanager.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppRoot() }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = androidx.lifecycle.viewmodel.compose
        .viewModel(factory = androidx.lifecycle.viewmodel
            .AndroidViewModelFactory.getInstance(
                androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner.current!!
                    .let { (it as ComponentActivity).application }
            ))

    NavHost(navController = navController, startDestination = "tasks") {
        composable("tasks") {
            TaskListScreen(
                viewModel = viewModel,
                onTaskClick = { id -> navController.navigate("edit/$id") },
                onAddClick = { navController.navigate("edit/0") }
            )
        }
        composable("edit/{taskId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
            TaskEditScreen(
                viewModel = viewModel,
                taskId = if (id == 0L) null else id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
