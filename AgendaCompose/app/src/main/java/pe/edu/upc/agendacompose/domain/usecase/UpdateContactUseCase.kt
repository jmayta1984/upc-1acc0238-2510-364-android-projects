package pe.edu.upc.agendacompose.domain.usecase

import pe.edu.upc.agendacompose.domain.model.Contact
import pe.edu.upc.agendacompose.domain.repository.ContactRepository

class UpdateContactUseCase(val repository: ContactRepository) {
    operator fun invoke(index: Int?, contact: Contact) {

        index?.let {
            repository.updateContact(it,  contact)

        }
    }
}