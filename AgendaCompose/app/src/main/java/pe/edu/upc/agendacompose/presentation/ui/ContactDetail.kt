package pe.edu.upc.agendacompose.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import pe.edu.upc.agendacompose.domain.model.Contact

@Preview
@Composable
fun ContactDetail(
    modifier: Modifier = Modifier,
    onSave: (Contact) -> Unit = {},
    onBack: () -> Unit = {}
) {

    val name = remember {
        mutableStateOf("")
    }

    val company = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val contact =
                        Contact(
                            name = name.value,
                            company = company.value,
                            phone = phone.value
                        )
                    onSave(contact)
                    onBack()
                }
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
            }
        }

    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Name") },
                value = name.value,
                onValueChange = {
                    name.value = it
                }
            )

            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Company") },
                value = company.value,
                onValueChange = {
                    company.value = it
                }
            )

            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Phone") },
                value = phone.value,
                onValueChange = {
                    phone.value = it
                }
            )

        }

    }

}