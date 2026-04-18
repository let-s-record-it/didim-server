package com.didim.api.support.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDate

class YearValidator : ConstraintValidator<ValidYear, Int> {

    override fun isValid(value: Int?, context: ConstraintValidatorContext?): Boolean {
        context?.disableDefaultConstraintViolation()

        if (value == null) {
            context?.buildConstraintViolationWithTemplate("연도는 null일 수 없습니다.")?.addConstraintViolation()
            return false
        }

        val maxYear = LocalDate.now().year + 25
        context?.buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate.format(maxYear))
            ?.addConstraintViolation()

        return value in 1900..maxYear
    }
}