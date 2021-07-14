package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.AccommodationType
import org.springframework.data.repository.CrudRepository

interface AccommodationTypeRepository : CrudRepository<AccommodationType, Long> {
}