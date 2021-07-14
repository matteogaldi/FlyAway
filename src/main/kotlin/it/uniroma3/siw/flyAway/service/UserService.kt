package it.uniroma3.siw.flyAway.service

import it.uniroma3.siw.flyAway.model.User
import it.uniroma3.siw.flyAway.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val db: UserRepository) {

    fun create(user: User): User = db.save(user)

    fun readBySurname(surname: String) : List<User> = db.findAllBySurname(surname)
}