package pe.edu.upc.agendacompose.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.agendacompose.data.repository.ContactRepositoryImpl
import pe.edu.upc.agendacompose.domain.model.Contact
import pe.edu.upc.agendacompose.domain.usecase.AddContactUseCase
import pe.edu.upc.agendacompose.domain.usecase.GetAllUseCase

@Preview
@Composable
fun Home() {

    val navController = rememberNavController()


    val repository = ContactRepositoryImpl()
    val getAllUseCase = GetAllUseCase(repository)
    val addContactUseCase = AddContactUseCase(repository)

    val contacts = getAllUseCase().collectAsState(emptyList())

    NavHost(navController = navController, startDestination = "ContactList") {

        composable("ContactList") {
            ContactList(contacts = contacts.value) {
                navController.navigate("ContactDetail")
            }
        }

        composable("ContactDetail") {
            ContactDetail(
                onSave = { contact ->
                    addContactUseCase(contact)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}