package it.uniroma3.siw.flyAway.service

import it.uniroma3.siw.flyAway.model.Credentials
import it.uniroma3.siw.flyAway.repository.CredentialsRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class CredentialsService(private val db: CredentialsRepository, private val passwordEncoder: PasswordEncoder) {

    fun create(credentials: Credentials): Credentials {
        credentials.password = passwordEncoder.encode(credentials.password)
        credentials.role = Credentials.DEFAULT_ROLE
        return db.save(credentials)
    }

    fun readByEmail(email: String): Credentials? = db.findByEmail(email).orElse(null)

}