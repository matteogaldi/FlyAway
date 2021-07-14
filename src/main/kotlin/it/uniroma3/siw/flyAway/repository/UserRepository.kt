package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {

    fun findAllBySurname(surname: String) : List<User>
}