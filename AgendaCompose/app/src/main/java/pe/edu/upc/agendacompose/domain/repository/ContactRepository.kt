package pe.edu.upc.agendacompose.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.edu.upc.agendacompose.domain.model.Contact

interface ContactRepository {
    fun getAll(): Flow<List<Contact>>
    fun addContact(contact: Contact)
    fun deleteContact()
    fun updateContact()
}