package pe.edu.upc.agendacompose.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.agendacompose.data.repository.ContactRepositoryImpl
import pe.edu.upc.agendacompose.domain.model.Contact
import pe.edu.upc.agendacompose.domain.usecase.AddContactUseCase
import pe.edu.upc.agendacompose.domain.usecase.DeleteContactUseCase
import pe.edu.upc.agendacompose.domain.usecase.GetAllUseCase
import pe.edu.upc.agendacompose.domain.usecase.UpdateContactUseCase

@Preview
@Composable
fun Home() {

    val navController = rememberNavController()


    val repository = ContactRepositoryImpl()
    val getAllUseCase = GetAllUseCase(repository)
    val addContactUseCase = AddContactUseCase(repository)
    val updateContactUseCase = UpdateContactUseCase(repository)
    val deleteContactUseCase = DeleteContactUseCase(repository)

    val contacts = getAllUseCase().collectAsState(emptyList())

    NavHost(navController = navController, startDestination = Routes.ContactList.route) {

        composable(Routes.ContactList.route) {
            ContactList(
                contacts = contacts.value,
                onAdd = {
                    navController.navigate("${Routes.ContactDetail.route}/-1")
                },
                onPressed = { id ->
                    navController.navigate("${Routes.ContactDetail.route}/$id")

                })


        }

        composable(
            route = Routes.ContactDetail.routeWithArgument,
            arguments = listOf(navArgument(Routes.ContactDetail.argument) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(Routes.ContactDetail.argument)
            var editingContact: Contact? = null
            id?.let {
                if (it != -1) {
                    editingContact = contacts.value[it]

                }
            }
            ContactDetail(
                contact = editingContact,
                onSave = { contact ->

                    if (editingContact == null) {
                        addContactUseCase(contact)
                    } else {
                        updateContactUseCase(id, contact)
                    }
                },
                onBack = {
                    navController.popBackStack()
                },
                onDelete = {
                    deleteContactUseCase(id)

                }
            )
        }
    }
}

sealed class Routes(val route: String) {

    data object ContactList : Routes(route = "ContactList")
    data object ContactDetail : Routes(route = "ContactDetail") {
        const val routeWithArgument = "ContactDetail/{id}"
        const val argument = "id"
    }
}