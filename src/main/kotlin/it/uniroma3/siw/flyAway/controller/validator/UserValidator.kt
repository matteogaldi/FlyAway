package it.uniroma3.siw.flyAway.controller.validator

import it.uniroma3.siw.flyAway.model.User
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class UserValidator : Validator {

    override fun supports(p0: Class<*>): Boolean {
        return User::class.java == p0
    }

    override fun validate(o: Any, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "required")
    }
}