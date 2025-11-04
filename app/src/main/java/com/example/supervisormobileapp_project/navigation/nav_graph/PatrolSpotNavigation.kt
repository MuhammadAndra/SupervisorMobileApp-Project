package com.example.supervisormobileapp_project.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.supervisormobileapp_project.NfcReaderViewModel
import com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot.AddEditPatrolSpotScreen
import com.example.supervisormobileapp_project.ui.screen.patrol_list.PatrolListScreen
import kotlinx.serialization.Serializable

@Serializable
data class PatrolList(val id: Int)

@Serializable
data class AddEditPatrol(val id: Int?)

fun NavGraphBuilder.patrolSpotGraph(
    navController: NavController,
    nfcViewModel: NfcReaderViewModel,
//    onEnableNfc: () -> Unit,
//    onDisableNfc: () -> Unit
) {
    composable<PatrolList> { backStackEntry ->
        val patrolList = backStackEntry.toRoute<PatrolList>()
        PatrolListScreen(
            id = patrolList.id,
            onBackClick = { navController.navigateUp() },
            onNavigateToAddEditPatrol = { id ->
                navController.navigate(AddEditPatrol(id))
            }
        )
    }
    composable<AddEditPatrol> { backStackEntry ->
        val addEditPatrol = backStackEntry.toRoute<AddEditPatrol>()
        AddEditPatrolSpotScreen(
            id = addEditPatrol.id,
            onBackClick = { navController.navigateUp() },
            nfcVm = nfcViewModel,
//            onEnableNfc = { onEnableNfc() },
//            onDisableNfc = { onDisableNfc() }
        )
    }
}