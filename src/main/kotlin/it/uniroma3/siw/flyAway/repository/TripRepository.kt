package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.Trip
import it.uniroma3.siw.flyAway.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TripRepository : CrudRepository<Trip, UUID> {

    fun findAllByUser_Id(id: UUID): List<Trip>

    fun findAllByEndDateAfter(now: String): List<Trip>

    fun findByUser(user: User): List<Trip>
}