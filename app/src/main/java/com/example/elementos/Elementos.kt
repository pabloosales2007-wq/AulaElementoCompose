package com.example.elementos

import android.app.DatePickerDialog
import android.widget.DatePicker

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elementos.ui.theme.ElementosTheme
import com.example.elementos.ui.theme.Titulo
import com.example.elementos.ui.theme.semFoco
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.filled.DateRange


/**
 * Campo de seleção de data
 *
 * @param valor Valor atual da data
 * @param dataSelecionada Data que o usuário selecionou
 * @param rotulo Dica do que precisa fazer com o usuário
 * @return Retorna a data selecionada do usuário
 */
@Composable
fun campoData(
    valor: String,
    dataSelecionada: (String) -> Unit,
    rotulo: String
) {
    val contexto = LocalContext.current
    val calendario = Calendar.getInstance()

    val seletorDeDataPopUp = DatePickerDialog(
        contexto,
        { _: DatePicker, ano: Int, mes: Int, dia: Int ->
            dataSelecionada("$dia/${mes + 1}/$ano")
        },
        calendario.get(Calendar.YEAR),
        calendario.get(Calendar.MONTH),
        calendario.get(Calendar.DAY_OF_MONTH)
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = valor,
            onValueChange = {},
            label = { Text(rotulo) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = rotulo,
                    tint = Titulo
                )
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable {
                    seletorDeDataPopUp.show()
                }
        )
    }
}
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
 * Param rotulo Indica do que o usuario vai digitar
 * param valorAtual Valor que se encontra no campo
 * Param valorAMUdar Valor que vai passar a ter depois de digitado
 * Return Valor que foi digitado
 */
@Composable
fun CampoTexto(
    rotulo : String
    , valorAtual : String
    , valorAMudar : (String) -> Unit,
    icone: ImageVector
)

{
    //TextField
    OutlinedTextField( //elemento mais minimalista
        value = valorAtual,
        label = {Text(rotulo)},
        onValueChange = valorAMudar,
        colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = semFoco, focusedBorderColor = Titulo, focusedLabelColor = Titulo, unfocusedLabelColor = semFoco),
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {Icon(icone, rotulo, tint = Titulo)}

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
        Titulo("Cadastro da Livraria", 32)
        Espaco(32)
        Titulo("Cadastro de Livros", 20)
        var titulo by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o titulo" ,
            titulo,
            valorAMudar = {it -> titulo = it},
            Icons.Default.Abc

        )

        var autor by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o nome do autor" ,
            autor,
            valorAMudar = {it -> autor = it},
            Icons.Default.Person

        )

        var lancamento by remember { mutableStateOf("") }
        campoData(
            lancamento,
            {novaData -> lancamento = novaData},
            "Selecione uma data"
        )

        var descricao by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite a descrição" ,
            descricao,
            valorAMudar = {it -> descricao = it},
            Icons.Default.Description

        )

        var editora by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite a Editora" ,
            editora,
            valorAMudar = {it -> editora = it},
            ImageVector.vectorResource(R.drawable.editora)

        )

        var quantidade by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite a quantidade de livros" ,
            quantidade,
            valorAMudar = {it -> quantidade = it},
            Icons.Default.Numbers

        )

        var preco by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o preço" ,
            preco,
            valorAMudar = {it -> preco = it},
            Icons.Default.Payment

        )

        var isbn by remember { mutableStateOf("")  }
        CampoTexto(
            rotulo = "Digite o ISBN" ,
            isbn,
            valorAMudar = {it -> isbn = it},
            Icons.Default.Info

        )



    }

}


/**
 * Exibe uma prévia da tela de cadastro no Android Studio.
 */
@Preview
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


