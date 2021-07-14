package it.uniroma3.siw.flyAway.controller

import it.uniroma3.siw.flyAway.controller.validator.CredentialsValidator
import it.uniroma3.siw.flyAway.controller.validator.UserValidator
import it.uniroma3.siw.flyAway.model.Credentials
import it.uniroma3.siw.flyAway.model.User
import it.uniroma3.siw.flyAway.service.CredentialsService
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class AuthenticationController(
    private val service: CredentialsService,
    private val credentialsValidator: CredentialsValidator,
    private val userValidator: UserValidator
) {

    @GetMapping("/register")
    fun registerForm(model: Model): String {
        model["credentials"] = Credentials()
        model["user"] = User()
        return "register"
    }

    @PostMapping("/register")
    fun registerUser(
        @ModelAttribute("user") user: User,
        userBindingResult: BindingResult,
        @ModelAttribute("credentials") credentials: Credentials,
        credentialsBindingResult: BindingResult
    ): String {
        this.credentialsValidator.validate(credentials, credentialsBindingResult)
        this.userValidator.validate(user, userBindingResult)
        if (!credentialsBindingResult.hasErrors() && !userBindingResult.hasErrors()) {
            credentials.user = user
            service.create(credentials)
            return "redirect:/trips"

        }
        return "register"
    }

    @GetMapping("/default")
    fun getHome() : String = "redirect:/trips"

    @GetMapping("/login")
    fun getLogin(): String = "login"

    @GetMapping("/logout")
    fun logout(): String = "index"
}