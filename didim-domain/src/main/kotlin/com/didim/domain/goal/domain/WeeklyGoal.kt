package com.didim.domain.goal.domain

import java.time.LocalDate

data class WeeklyGoal(
    val title: GoalTitle,
    val description: GoalDescription,
    val period: WeeklyGoalPeriod,
    val scheduleCategoryId: Long,
    val calendarId: Long,
    val monthlyGoalId: Long,
    val achieved: Boolean = false,
    val id: Long? = null,
) {
    constructor(
        title: String,
        description: String,
        week: Int,
        startDate: LocalDate,
        endDate: LocalDate,
        scheduleCategoryId: Long,
        calendarId: Long,
        monthlyGoalId: Long,
    ) : this(
        GoalTitle(title),
        GoalDescription(description),
        WeeklyGoalPeriod(week, startDate, endDate),
        scheduleCategoryId,
        calendarId,
        monthlyGoalId,
    )

    val titleValue: String
        get() = title.title

    val descriptionValue: String
        get() = description.description

    val week: Int
        get() = period.week

    val startDate: LocalDate
        get() = period.startDate

    val endDate: LocalDate
        get() = period.endDate
}
