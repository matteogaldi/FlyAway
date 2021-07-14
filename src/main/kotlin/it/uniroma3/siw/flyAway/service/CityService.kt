package it.uniroma3.siw.flyAway.service

import it.uniroma3.siw.flyAway.model.City
import it.uniroma3.siw.flyAway.repository.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(private val db: CityRepository) {

    fun findAll(): MutableIterable<City> = db.findAll()
}