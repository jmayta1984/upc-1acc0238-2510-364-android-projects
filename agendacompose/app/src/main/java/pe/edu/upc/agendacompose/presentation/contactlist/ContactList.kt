package pe.edu.upc.agendacompose.presentation.contactlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upc.agendacompose.domain.Contact

@Preview
@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    navigateTo: () -> Unit = { }

) {

    val contacts = remember {
        mutableStateOf(emptyList<Contact>())
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigateTo()
            }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    )

    { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {
            items(contacts.value) { contact ->
                ContactListItem(contact)
            }
        }
    }
}


@Composable
fun ContactListItem(contact: Contact) {
    Column {
        Text(
            contact.name,
            modifier = Modifier.padding(),
            fontWeight = FontWeight.Bold
        )
        Text(contact.company)
        Text(contact.phone)
    }
}