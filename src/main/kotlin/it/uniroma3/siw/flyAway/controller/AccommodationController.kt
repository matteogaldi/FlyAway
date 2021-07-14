package it.uniroma3.siw.flyAway.controller

import it.uniroma3.siw.flyAway.service.AccommodationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AccommodationController(private val service: AccommodationService) {

    @GetMapping("/accommodations")
    fun readAllAccommodations(model: Model): String {
        model["accommodationList"] = service.readByPrice()
        return "user/accommodationList"
    }
}