package com.example.pinlikest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pinlikest.ui.theme.PinlikestTheme

class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PinlikestTheme {
                AppNavigation()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val pins = remember { PinsDatabase.pinsData.shuffled() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Para Você") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,

                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    IconButton(onClick = {
                        Log.d("botaoHome", "usuario-clicouHome_route")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = {
                        Log.d("botaoSearch", "usuario-clicouSearch_route")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = {
                        Log.d("botaoCreate/Upload", "usuario-clicouCreate/Upload_route")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = {
                        Log.d("botaoMessages", "usuario-clicouMessages_route")
                    }) {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = {
                        Log.d("botaoUserProfile", "usuario-clicouUserProfile_route")
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        },
        content = { paddingValues ->
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    pins.filterIndexed { index, _ -> index % 2 == 0 } // pega os pins pares
                        .forEach { pin ->
                            PinHomeTemplate(pin)
                        }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    pins.filterIndexed { index, _ -> index % 2 != 0 } // pega os impares
                        .forEach { pin ->
                            PinHomeTemplate(pin)
                        }
                }
            }
        }
    )
}
@Composable
fun PinHomeTemplate(pin: Pin) {
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = ShapeDefaults.ExtraSmall,
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant))
    ) {
    Image(
        painter = painterResource(pin.image),
        modifier = Modifier
            .fillMaxWidth()
            .clickable
            {
                val intent = Intent(context, PinDetailsActivity::class.java)
                Log.d("ButaoPin", "UserPinDetailsButton")

                intent.putExtra("pinNome", pin.pinNome)
                intent.putExtra("pinImg", pin.image)
                intent.putExtra("pinCriador", pin.pinCriador)
                intent.putExtra("pinCriador", pin.pinTopComentario)
                context.startActivity(intent)
            },
        contentDescription = "ImagePin"
    )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .height(24.dp)
        ) {
            Text(pin.pinNome)

            IconButton(
                onClick = {
                    val intent = Intent(context, PinDetailsActivity::class.java)
                    Log.d("ButaoPin", "UserPinDetailsButton")

                    intent.putExtra("pinNome", pin.pinNome)
                    intent.putExtra("pinImg", pin.image)
                    intent.putExtra("pinCriador", pin.pinCriador)
                    intent.putExtra("pinCriador", pin.pinTopComentario)
                    context.startActivity(intent)
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}