package pe.edu.upc.agendacompose.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upc.agendacompose.domain.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ContactDetail(
    modifier: Modifier = Modifier,
    contact: Contact? = null,
    onSave: (Contact) -> Unit = {},
    onBack: () -> Unit = {},
    onDelete: () -> Unit = {}
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

    contact?.let {
        name.value = it.name
        phone.value = it.phone
        company.value = it.company
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                actions = {
                    IconButton(
                        onClick = {
                            contact?.let {
                                //onBack()
                                onDelete()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    val contact =
                        Contact(
                            id = (0..999).random(),
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