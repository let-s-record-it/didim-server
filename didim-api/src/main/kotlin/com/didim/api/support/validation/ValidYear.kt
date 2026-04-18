package com.didim.api.support.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import jakarta.validation.constraints.NotNull
import kotlin.reflect.KClass

@NotNull
@ReportAsSingleViolation
@Constraint(validatedBy = [YearValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ValidYear(
    val message: String = "연도는 1900 이상 %d 이하여야 합니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
