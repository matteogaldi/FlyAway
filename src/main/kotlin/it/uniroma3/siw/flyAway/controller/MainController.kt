package it.uniroma3.siw.flyAway.controller

import it.uniroma3.siw.flyAway.service.AccommodationService
import it.uniroma3.siw.flyAway.service.CityService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController(private val cityService: CityService, private val accommodationService: AccommodationService) {

    @GetMapping("/")
    fun getIndex(): String = "index"


    @GetMapping("/search")
    fun getSearchPage(model: Model): String {
        model["cities"] = cityService.findAll()
        return "user/search"
    }

    @GetMapping("/search/query")
    fun getResults(
        model: Model,
        @RequestParam("city") city: Long,
        @RequestParam("animals") animals: Boolean?,
        @RequestParam("rating") rating: Int
    ): String {
        model["accommodations"] = accommodationService.readForSearch(city, false, rating)
        animals?.let {
            model["accommodations"] = accommodationService.readForSearch(city, it, rating)
            return "user/accommodationList"
        }

        return "user/accommodationList"
    }
}