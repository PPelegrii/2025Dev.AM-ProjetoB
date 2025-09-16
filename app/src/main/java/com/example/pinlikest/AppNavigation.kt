package com.example.pinlikest

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "TelaLogo") {

        composable("TelaLogo") {
            TelaLogo(
                toHome = { navController.navigate("HomeScreen") }
            )
        }

        composable("HomeScreen") {
            HomeScreen(
                onClickPinDetails = { pin -> navController.navigate(
                    "PinDetails/${pin.image}/${pin.pinNome}/${pin.pinCriador}/${pin.pinTopComentario}"
                ) },

                toMessages = { navController.navigate("MessagesScreen") },
                toProfile = { navController.navigate("PerfilScreen") }
            )
        }
        composable("PerfilScreen") {
            PerfilScreen(
                pinDetails = { pin -> navController.navigate(
                    "PinDetails/${pin.image}/${pin.pinNome}/${pin.pinCriador}/${pin.pinTopComentario}"
                ) },

                toHome = { navController.popBackStack("HomeScreen", false) },
                toMessages = { navController.navigate("MessagesScreen") },
            )
        }
        composable("MessagesScreen") {
            MessagesScreen(
                toHome = { navController.popBackStack("HomeScreen", false) },
                toProfile = { navController.navigate("PerfilScreen") }
            )
        }

        composable(
            "PinDetails/{pinImg}/{pinNome}/{pinCriador}/{pinTopComentario}",
            arguments = listOf(
                navArgument("pinImg") { type = NavType.IntType },
                navArgument("pinNome") { type = NavType.StringType },
                navArgument("pinCriador") { type = NavType.StringType },
                navArgument("pinTopComentario") { type = NavType.StringType }
            )
        ) { backstackEntry ->

            val pinImg = backstackEntry.arguments!!.getInt("pinImg")
            val pinNome = backstackEntry.arguments?.getString("pinNome") ?: ""
            val pinCriador = backstackEntry.arguments?.getString("pinCriador")
            val pinTopComentario = backstackEntry.arguments?.getString("pinTopComentario")

            PinDetails(
                pinImg,
                pinNome,
                pinCriador,
                pinTopComentario,
                onBack = { navController.popBackStack() }
            )
        }
    }
}