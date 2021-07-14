package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.Credentials
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CredentialsRepository : CrudRepository<Credentials, UUID> {

    fun findByEmail(email: String): Optional<Credentials>
}