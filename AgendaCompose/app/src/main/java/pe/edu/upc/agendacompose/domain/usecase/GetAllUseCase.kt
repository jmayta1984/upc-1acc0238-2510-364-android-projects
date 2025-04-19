package pe.edu.upc.agendacompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.edu.upc.agendacompose.domain.model.Contact
import pe.edu.upc.agendacompose.domain.repository.ContactRepository

class GetAllUseCase(val repository: ContactRepository) {

    operator fun invoke(): Flow<List<Contact>>{
        return repository.getAll()
    }
}