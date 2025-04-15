package pe.edu.upc.agendacompose.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.agendacompose.presentation.contact.ContactDetail
import pe.edu.upc.agendacompose.presentation.contactlist.ContactList

@Preview
@Composable
fun Home() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.ContactList.route){
        composable(route = Routes.ContactList.route) {
            ContactList {
                navController.navigate(route = Routes.ContactDetail.route)
            }
        }
        composable(route = Routes.ContactDetail.route) {
            ContactDetail {
                navController.popBackStack()
            }
        }
    }
}

sealed class Routes(val route: String ){
    data object ContactList: Routes(route = "ContactList")
    data object ContactDetail: Routes(route = "ContactDetail")
}