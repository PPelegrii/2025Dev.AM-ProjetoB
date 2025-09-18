package com.example.pinlikest

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    context: Context,
    toHome:() -> Unit,
    toProfile:() -> Unit
) {
    val mensagens = MensagensDatabase.mensagensData

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text("Caixa de Entrada") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                Log.d("botaoCreateMessage", "usuario-clicouCreateMessage")
                            }) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxHeight(.087f),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,

                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        toHome()
                        Log.d("botaoHome", "usuario-clicouHome_route")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(onClick = {
                        Log.d("botaoSearch", "usuario-clicouSearch_route")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(onClick = {
                        Log.d("botaoCreate/Upload", "usuario-clicouCreate/Upload_route")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                    IconButton(onClick = {
                        toProfile()
                        Log.d("botaoUserProfile", "usuario-clicouUserProfile_route")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        MessageController(
                            context = context,
                            addMessage = { newMessage ->
                                MensagensDatabase.mensagensData.add(newMessage)
                            },
                        )
                    }
                }
                items(mensagens) { mensagem ->
                    MensagemTemplate(context, mensagem)
                }
            }
        }
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MensagemTemplate(context: Context, mensagem: Mensagem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        shape = ShapeDefaults.ExtraLarge,
        modifier = Modifier
            .padding(top = 2.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Log.d("usuarioGetMensagem", "usuarioClicouMensagem")
                },
                onLongClick = {
                    MensagensDatabase.mensagensData.remove(mensagem)

                    botaoAlerta(context, "Mensagem removida :(")
                    Log.d("usuarioLongPressMensagem", "usuarioPressionouMensagem")
                }
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = mensagem.mensagemTitulo,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "De: " + mensagem.mensagemRemetente,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = mensagem.mensagemDescricao,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    Spacer(modifier = Modifier.height(8.dp))
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessageController(context: Context, addMessage: (Mensagem) -> Unit) {

    var messageTitulo by remember { mutableStateOf("") }
    var messageDescricao by remember { mutableStateOf("") }
    var messageRemetente by remember { mutableStateOf("") }
    var messageDestinatario by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = messageTitulo,
                    onValueChange = { messageTitulo = it },
                    placeholder = { Text("Título") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = messageDescricao,
                    onValueChange = { messageDescricao = it },
                    placeholder = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = messageRemetente,
                    onValueChange = { messageRemetente = it },
                    placeholder = { Text("Remetente") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = messageDestinatario,
                    onValueChange = { messageDestinatario = it },
                    placeholder = { Text("Destinatario") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    shape = ShapeDefaults.ExtraSmall,
                    onClick = {
                        if (messageTitulo.isNotBlank() && messageDestinatario.isNotBlank()) {
                            addMessage(
                                Mensagem(
                                    mensagemTitulo = messageTitulo,
                                    mensagemDescricao = messageDescricao,
                                    mensagemRemetente = messageRemetente,
                                    mensagemDestinatario = messageDestinatario
                                )
                            )

                            messageTitulo = ""
                            messageDescricao = ""
                            messageRemetente = ""
                            messageDestinatario = ""

                            botaoAlerta(context, "Mensagem adicionada :)")
                        Log.d("sendMessage", "mensagem-adicionada")
                        }
                    }
                ) { Text("Mandar messagem!") }
            }
        }
    }
}
fun botaoAlerta(context: Context, mensagem: String) {
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}