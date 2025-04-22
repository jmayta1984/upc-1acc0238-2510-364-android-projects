package pe.edu.upc.agendacompose.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upc.agendacompose.domain.model.Contact

@Preview
@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    contacts: List<Contact> = emptyList(),
    onAdd: () -> Unit = {},
    onPressed: (Int) -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAdd()
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    )

    { padding ->
        LazyColumn(modifier = modifier.padding(padding)) {

            itemsIndexed(contacts) { index, contact ->
                ContactListItem(
                    index = index,
                    contact = contact,
                    onPressed = onPressed)

            }

        }
    }
}

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier,
    index: Int,
    contact: Contact,
    onPressed: (Int) -> Unit = {}
) {
    Card(
        onClick = {
            onPressed(index)
        },
        modifier = modifier.padding(8.dp)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(contact.name, fontWeight = FontWeight.Bold)
            Text(contact.company)
        }
    }
}