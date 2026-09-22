package com.example.notaviva.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notaviva.ui.screens.*
import com.example.notaviva.viewmodel.CaseViewModel

private object Routes {
    const val HOME = "home"
    const val LIST = "list"
    const val FORM = "form?caseId={caseId}"
    const val DETAIL = "detail/{caseId}"

    fun form(caseId: Long? = null) = if (caseId == null) "form" else "form?caseId=$caseId"
    fun detail(caseId: Long) = "detail/$caseId"
}

@Composable
fun NotaVivaNavHost(viewModel: CaseViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onNewCase = { navController.navigate(Routes.form()) },
                onMyCases = { navController.navigate(Routes.LIST) }
            )
        }

        composable(Routes.LIST) {
            CaseListScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNewCase = { navController.navigate(Routes.form()) },
                onOpenCase = { id -> navController.navigate(Routes.detail(id)) }
            )
        }

        composable(
            route = Routes.FORM,
            arguments = listOf(navArgument("caseId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) { backStackEntry ->
            val rawId = backStackEntry.arguments?.getLong("caseId") ?: -1L
            val caseId = if (rawId == -1L) null else rawId
            CaseFormScreen(
                viewModel = viewModel,
                caseId = caseId,
                onBack = { navController.popBackStack() },
                onSaved = { savedId ->
                    navController.popBackStack()
                    if (caseId == null) {
                        navController.navigate(Routes.detail(savedId))
                    }
                }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("caseId") { type = NavType.LongType })
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getLong("caseId") ?: return@composable
            CaseDetailScreen(
                viewModel = viewModel,
                caseId = caseId,
                onBack = { navController.popBackStack() },
                onEdit = { id -> navController.navigate(Routes.form(id)) },
                onDeleted = { navController.popBackStack(Routes.LIST, inclusive = false) }
            )
        }
    }
}
