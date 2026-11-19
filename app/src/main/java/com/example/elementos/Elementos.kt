package com.example.elementos

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elementos.ui.theme.ElementosTheme
import com.example.elementos.ui.theme.Purple40
import com.example.elementos.ui.theme.Titulo
import com.example.elementos.ui.theme.semFoco

/**
 * Cria um espaço vertical entre os elementos.
 * @param tamanho Define a altura do espaço em dp.
 */
@Composable
fun Espaco(tamanho: Int){
    Spacer(modifier = Modifier.height(tamanho.dp
    ))
}

/**
 * Cria um título na tela.
 * @param texto Texto que será exibido.
 * @param tamanho Define o tamanho da fonte em sp.
 */
@Composable
fun Titulo(texto: String, tamanho: Int){
    Text(
        text = texto,
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = tamanho.sp,
        color = Titulo,
        fontWeight = FontWeight.SemiBold
    )
}

/**
 * Cria um elemento visual de campo, onde o usuario pode digitar
 *
 * Param rotulo indica do que o usuario vai digitar
 * param valorAtual valor que se encontra no campo
 * Param valorAMUdar valor que vai passar a ter depois de digitado
 * Return Valor que foi digitado
 */
@Composable
fun CampoTexto(
    rotulo : String
    , valorAtual : String
    , valorAMudar : (String) -> Unit)
{
    //TextField
    OutlinedTextField( //elemento mais minimalista
        value = valorAtual,
        label = {Text(rotulo)},
        onValueChange = valorAMudar,
        colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = semFoco, focusedBorderColor = Titulo, focusedLabelColor = Titulo, unfocusedLabelColor = semFoco),
        modifier = Modifier.fillMaxWidth()

    )

}


/**
 * Organiza e contém os elementos visuais da tela.
 * @param valorDeEspacamentoInicial Espaçamento inicial fornecido pelo Scaffold.
 */
@Composable
fun ContendoElementos(valorDeEspacamentoInicial: PaddingValues) {
    val lembrarRolagem = rememberScrollState()
    Column(modifier = Modifier.padding(
        valorDeEspacamentoInicial

    )
        .padding(horizontal = 16.dp)
        .fillMaxHeight()
        .verticalScroll(lembrarRolagem)
    ) {
        Titulo("Cadastro de Livro", 32)
        Espaco(32)
        Titulo("Cadastro de Pessoas", 20)
        var titulo by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o titulo" ,
            titulo,
            valorAMudar = {it -> titulo = it}

        )

        var autor by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o nome do autor" ,
            autor,
            valorAMudar = {it -> autor = it}

        )

        var descricao by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite a descrição" ,
            descricao,
            valorAMudar = {it -> descricao = it}

        )

        var quantidade by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite a quantidade de livros" ,
            quantidade,
            valorAMudar = {it -> quantidade = it}

        )

        var preco by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o preço" ,
            preco,
            valorAMudar = {it -> preco = it}

        )

        var isbn by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o ISBN" ,
            isbn,
            valorAMudar = {it -> isbn = it}

        )



    }

}
@Preview

/**
 * Exibe uma prévia da tela de cadastro no Android Studio.
 */
@Composable

fun TelaPrevista() {
    ElementosTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) { it ->
            ContendoElementos(it)

        }
    }
}

