package pe.edu.upc.agendacompose.presentation.contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ContactDetail(
    saveContact: () -> Unit = {}
) {
    val name = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }

    val company = remember {
        mutableStateOf("")
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    saveContact()
                }
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
            }
        }
    )

    { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = name.value,
                placeholder = {
                    Text("Name")
                },
                onValueChange = {
                    name.value = it
                })
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), value = phone.value,
                placeholder = {
                    Text("Phone")
                },
                onValueChange = {
                    phone.value = it
                })
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), value = company.value,
                placeholder = {
                    Text("Company")
                },
                onValueChange = {
                    company.value = it
                })
        }
    }

}