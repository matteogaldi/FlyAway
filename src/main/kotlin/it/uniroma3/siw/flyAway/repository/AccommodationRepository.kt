package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.Accommodation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AccommodationRepository : CrudRepository<Accommodation, UUID> {

    fun findAllByAddress_City_Name(city: String): List<Accommodation>

    fun findAllByType_Id(id: String): List<Accommodation>

    fun findAllByType_AnimalsAllowed(animalsAllowed: Boolean): List<Accommodation>

    fun findAllByRatingGreaterThan(rating: Number): List<Accommodation>

    fun findAllByPriceLessThanEqual(price: Number): List<Accommodation>

    @Query("select * from accommodations order by price asc", nativeQuery = true)
    fun findAllByPriceAsc(): List<Accommodation>

    fun findAllByAddress_City_IdAndRatingAndType_AnimalsAllowed(
        address_city_id: Long,
        rating: Int,
        type_animalsAllowed: Boolean
    ): List<Accommodation>
}