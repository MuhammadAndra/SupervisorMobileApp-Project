package com.example.supervisormobileapp_project.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.supervisormobileapp_project.ui.screen.patrol_list.PatrolListScreen
import kotlinx.serialization.Serializable

@Serializable
data class PatrolList(val id:Int)

@Serializable
data class AddEditPatrol(val id: Int?)

fun NavGraphBuilder.patrolSpotGraph(navController: NavController) {
    composable<PatrolList> { backStackEntry->
        val patrolList = backStackEntry.toRoute<PatrolList>()
        PatrolListScreen(
            id = patrolList.id,
            onBackClick = { navController.popBackStack() },
            onNavigateToAddEditPatrol = { id -> AddEditPatrol(id) }
        )
    }
}