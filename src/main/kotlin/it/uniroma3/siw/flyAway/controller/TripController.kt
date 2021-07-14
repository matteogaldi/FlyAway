package it.uniroma3.siw.flyAway.controller

import it.uniroma3.siw.flyAway.model.*
import it.uniroma3.siw.flyAway.service.AccommodationService
import it.uniroma3.siw.flyAway.service.CityService
import it.uniroma3.siw.flyAway.service.CredentialsService
import it.uniroma3.siw.flyAway.service.TripService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@Controller
class TripController(
    private val service: TripService,
    private val credentialsService: CredentialsService,
    private val cityService: CityService,
    private val accommodationService: AccommodationService
) {

    @GetMapping("/trips")
    fun getTripsByUser(model: Model, principal: Principal): String {
        val credentials = credentialsService.readByEmail(principal.name)
        if (credentials != null) {
            model["tripList"] = service.readByUser(credentials.user)
        };
        return "user/home"
    }

    @GetMapping("/trip/{id}")
    fun getTrip(@PathVariable id: UUID, model: Model, principal: Principal): String {
        val credentials = credentialsService.readByEmail(principal.name)
        val trip = service.read(id)
        if (credentials != null) {
            if (credentials.user == trip.user) {
                model["trip"] = trip
                return "user/trip"
            }
        }
        return "error"
    }

    @GetMapping("/trip/delete/{id}")
    fun deleteTrip(@PathVariable id: UUID, principal: Principal): String {
        val credentials = credentialsService.readByEmail(principal.name)
        val trip = service.read(id)
        if (credentials != null) {
            if (credentials.user == trip.user) {
                service.delete(trip)
                return "redirect:/default"
            }
        }
        return "error"
    }

    @GetMapping("/trip/new/{accommodationId}")
    fun createNewTrip(
        model: Model,
        @PathVariable() accommodationId: UUID,
        principal: Principal
    ): String {
        val user = credentialsService.readByEmail(principal.name)?.user
        val trip = Trip()

        accommodationService.readById(accommodationId).let {
            trip.accommodation = it.get()
            trip.city = it.get().address.city
        }

        trip.user = user

        model["trip"] = trip
        return "user/tripForm"
    }

    @PostMapping("/trip/new/{id}")
    fun bookNewTrip(@PathVariable() id: UUID, principal: Principal, @ModelAttribute("trip") tripDates: Trip): String {
        val user = credentialsService.readByEmail(principal.name)?.user
        val trip = Trip()
        accommodationService.readById(id).let {
            trip.accommodation = it.get()
            trip.city = it.get().address.city
        }
        trip.user = user
        trip.startDate = tripDates.startDate
        trip.endDate = tripDates.endDate

        service.create(trip)
        return "redirect:/default"
    }
}