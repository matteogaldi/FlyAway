package it.uniroma3.siw.flyAway.controller.validator

import it.uniroma3.siw.flyAway.model.Credentials
import it.uniroma3.siw.flyAway.service.CredentialsService
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class CredentialsValidator(private val credentialsService: CredentialsService) : Validator {

    private val MIN_PASSWORD_LENGTH = 6

    override fun supports(clazz: Class<*>): Boolean {
        return Credentials::class.java == clazz
    }

    override fun validate(o: Any, errors: Errors) {
        val credentials = o as Credentials
        val email: String = credentials.email.trim()
        val password = credentials.password.trim()

        if (email.isEmpty()) {
            errors.reject("email", "required")
        } else if (credentialsService.readByEmail(email) != null) {
            errors.reject("email", "duplicate")
        }

        if (password.isEmpty()) {
            errors.reject("password", "required")
        } else if (password.length < MIN_PASSWORD_LENGTH) {
            errors.reject("password", "size")
        }
    }
}