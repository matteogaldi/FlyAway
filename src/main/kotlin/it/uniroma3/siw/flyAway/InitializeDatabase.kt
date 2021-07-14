package it.uniroma3.siw.flyAway

import it.uniroma3.siw.flyAway.model.*
import it.uniroma3.siw.flyAway.repository.*
import it.uniroma3.siw.flyAway.service.CredentialsService
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class InitializeDatabase(
    private val credentialsService: CredentialsService,
    private val accommodationRepository: AccommodationRepository,
    private val tripRepository: TripRepository,
    private val addressRepository: AddressRepository,
    private val cityRepository: CityRepository,
    private val accommodationTypeRepository: AccommodationTypeRepository
) :
    ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(p0: ContextRefreshedEvent) {

        val user = User()
        user.name = "Matteo"
        user.surname = "Galdi"
        user.dateOfBirth = "1998-07-09"

        val user2 = User()
        user2.name = "Mario"
        user2.surname = "Rossi"
        user2.dateOfBirth = "1998-07-09"


        val credentials = Credentials()
        credentials.user = user
        credentials.email = "mat.galdi1@stud.uniroma3.it"
        credentials.password = "123456"
        credentials.role = Credentials.DEFAULT_ROLE

        val credentials2 = Credentials()
        credentials2.user = user2
        credentials2.email = "mario.rossi@gmail.com"
        credentials2.password = "123456"
        credentials2.role = Credentials.DEFAULT_ROLE

        credentialsService.create(credentials)
        credentialsService.create(credentials2)

        val city = City()
        city.name = "Roma"
        city.state = "Italia"

        val city2 = City()
        city2.name = "Milano"
        city2.state = "Italia"

        val city3 = City()
        city3.name = "Firenze"
        city3.state = "Italia"

        cityRepository.saveAll(mutableListOf(city, city2, city3))

        val address = Address()
        address.city = city
        address.state = "italia"
        address.street = "Via veneto"
        address.streetNumber = 2
        address.zipCode = 12345

        val address1 = Address()
        address1.city = city2
        address1.state = "italia"
        address1.street = "Piazza ungheria"
        address1.streetNumber = 5
        address1.zipCode = 12345

        val address2 = Address()
        address2.city = city3
        address2.state = "italia"
        address2.street = "Viale dei caduti"
        address2.streetNumber = 127
        address2.zipCode = 12345

        addressRepository.saveAll(mutableListOf(address, address1, address2))

        val accommodationType = AccommodationType()
        accommodationType.animalsAllowed = false
        accommodationType.description = "luxury"

        val accommodationType1 = AccommodationType()
        accommodationType1.animalsAllowed = true
        accommodationType1.description = "b&b"

        val accommodationType2 = AccommodationType()
        accommodationType2.animalsAllowed = false
        accommodationType2.description = "albergo"

        accommodationTypeRepository.saveAll(mutableListOf(accommodationType, accommodationType1, accommodationType2))

        val accommodation = Accommodation()
        accommodation.name = "Hilton"
        accommodation.price = 789F
        accommodation.rating = 5
        accommodation.type = accommodationType
        accommodation.address = address

        val accommodation1 = Accommodation()
        accommodation1.name = "Pensione Da rosa"
        accommodation1.price = 30F
        accommodation1.rating = 5
        accommodation1.type = accommodationType1
        accommodation1.address = address1

        val accommodation2 = Accommodation()
        accommodation2.name = "Villa Ada"
        accommodation2.price = 150F
        accommodation2.rating = 5
        accommodation2.type = accommodationType2
        accommodation2.address = address2

        accommodationRepository.saveAll(mutableListOf(accommodation, accommodation1, accommodation2))

        val trip = Trip()
        trip.user = user2
        trip.accommodation = accommodation
        trip.city = city
        trip.startDate = "2021-07-15"
        trip.endDate = "2021-07-20"

        val trip2 = Trip()
        trip2.user = user2
        trip2.accommodation = accommodation1
        trip2.city = city
        trip2.startDate = "2021-07-23"
        trip2.endDate = "2021-07-30"

        tripRepository.saveAll(mutableListOf(trip, trip2))
    }
}