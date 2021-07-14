package it.uniroma3.siw.flyAway.service

import it.uniroma3.siw.flyAway.model.Accommodation
import it.uniroma3.siw.flyAway.repository.AccommodationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccommodationService(private val db: AccommodationRepository) {

    fun create(accommodation: Accommodation) = db.save(accommodation)

    fun readByPrice(): List<Accommodation> = db.findAllByPriceAsc()

    fun readForSearch(city: Long, animals: Boolean, rating: Int): List<Accommodation> =
        db.findAllByAddress_City_IdAndRatingAndType_AnimalsAllowed(city, rating, animals)

    fun readById(id: UUID): Optional<Accommodation> = db.findById(id)

    fun readByCityName(name: String): List<Accommodation> = db.findAllByAddress_City_Name(name)

    fun readByTypeID(id: String): List<Accommodation> = db.findAllByType_Id(id)

    fun readByAnimalAllowed(allowed: Boolean): List<Accommodation> = db.findAllByType_AnimalsAllowed(allowed)

    fun readRatingGreaterThan(rating: Number): List<Accommodation> = db.findAllByRatingGreaterThan(rating)

    fun readByPriceLessThanEqual(price: Number): List<Accommodation> = db.findAllByPriceLessThanEqual(price)

    fun update(accommodation: Accommodation) = create(accommodation)

    fun delete(accommodation: Accommodation) = db.delete(accommodation)
}