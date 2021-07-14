package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.City
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CityRepository : CrudRepository<City, UUID> {

    fun findAllByName(name: String): List<City>

    fun findAllByState(name: String): List<City>
}