package com.example.pinlikest

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = "TelaLogo"){

        composable("TelaLogo") {
            //TelaLogo(toHome = { navController.navigate("HomeScreen") }) }

        //composable("HomeScreen") {
            HomeScreen(navController) }

        /*composable("PinDetails") {
            backstackEntry ->

            var pin: Pin

            PinDetails(pinDetails = { navController.navigate("PinDetails") }
            ) }*/

        /*composable("PinDetailsActivity") {
            backstackEntry ->
            var pin: Pin
            val pinImg =
            val pinNome: String
            val pinCriador: String?
            val pinTopComentario: String?
            //PinDetails
        }*/
        /*composable("tela2/{nome}") {
                backstackEntry ->
            val nome = backstackEntry.arguments?.getString("nome") ?: ""

            Tela2(nome, onBack = { navController.popBackStack() } )
        }*/
    }
}
// não aqui né bobão
@Composable
fun Tela1(navController: NavHostController) {
    val nome = "blah"

    Button(
        onClick = {
            navController.navigate("tela2/$nome")
        }
    ) { Text("Abrir tela 2") }
}
@Composable
fun Tela2(nome: String, onBack:() -> Unit) {

    Text("Bem vindo $nome! :)")

    Button(
        onClick = {
            //navController.navigate("home")
            //navController.popBackStack() // mais otimizado
            onBack()
        }
    ) { Text("Voltar pra tela 1") }
}