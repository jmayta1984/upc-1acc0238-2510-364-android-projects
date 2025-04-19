package pe.edu.upc.agendacompose.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.edu.upc.agendacompose.domain.model.Contact
import pe.edu.upc.agendacompose.domain.repository.ContactRepository

class ContactRepositoryImpl : ContactRepository {

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    override fun getAll(): Flow<List<Contact>> {
        return _contacts
    }

    override fun addContact(contact: Contact) {
        _contacts.value += contact
    }

    override fun deleteContact() {
        TODO("Not yet implemented")
    }

    override fun updateContact() {
        TODO("Not yet implemented")
    }
}