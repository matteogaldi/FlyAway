package it.uniroma3.siw.flyAway.service

import it.uniroma3.siw.flyAway.model.Trip
import it.uniroma3.siw.flyAway.model.User
import it.uniroma3.siw.flyAway.repository.TripRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TripService(private val db: TripRepository) {

    fun create(trip: Trip): Trip = db.save(trip)

    fun read(id: UUID): Trip = db.findById(id).orElse(null)

    fun readByUser(user: User) : List<Trip> = db.findByUser(user)

    fun delete(trip: Trip) = db.delete(trip)

    fun deleteById(id: UUID) = db.deleteById(id)

    fun readByUserId(id: UUID): List<Trip> = db.findAllByUser_Id(id)

    fun readByUpcoming(today: String): List<Trip> = db.findAllByEndDateAfter(today)
}