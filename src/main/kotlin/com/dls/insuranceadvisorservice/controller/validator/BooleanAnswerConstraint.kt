package com.dls.insuranceadvisorservice.controller.validator

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [BooleanAnswer::class])
annotation class BooleanList(
    val message: String = "The values must be 1 or 0",
    val groups: Array<KClass<out String>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class BooleanAnswer : ConstraintValidator<BooleanList, List<Int>> {

    override fun isValid(value: List<Int>, context: ConstraintValidatorContext?): Boolean {
       return value.all { it == 0 || it == 1 }
    }
}