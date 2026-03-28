package com.didim.domain.goal.domain

import java.time.LocalDate

data class MonthlyGoal(
    val title: GoalTitle,
    val description: GoalDescription,
    val period: MonthlyGoalPeriod,
    val scheduleCategoryId: Long,
    val calendarId: Long,
    val achieved: Boolean = false,
    val id: Long? = null,
) {

    constructor(
        title: String,
        description: String,
        startDate: LocalDate,
        endDate: LocalDate,
        scheduleCategoryId: Long,
        calendarId: Long,
    ) : this(
        GoalTitle(title),
        GoalDescription(description),
        MonthlyGoalPeriod(startDate, endDate),
        scheduleCategoryId,
        calendarId,
    )

    val titleValue: String
        get() = title.title

    val descriptionValue: String
        get() = description.description

    val startDate: LocalDate
        get() = period.startDate

    val endDate: LocalDate
        get() = period.endDate
}
