package it.uniroma3.siw.flyAway.repository

import it.uniroma3.siw.flyAway.model.Address
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AddressRepository : CrudRepository<Address, UUID> {
}