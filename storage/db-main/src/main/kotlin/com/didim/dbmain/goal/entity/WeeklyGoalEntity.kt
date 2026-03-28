package com.didim.dbmain.goal.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "weekly_goal")
@Entity
class WeeklyGoalEntity(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var description: String,
    @Column(nullable = false)
    var week: Int,
    @Column(nullable = false)
    var startDate: LocalDate,
    @Column(nullable = false)
    var endDate: LocalDate,
    @Column(nullable = false)
    var achieved: Boolean,
    @Column(nullable = false)
    var monthlyGoalId: Long,
    @Column(nullable = false)
    var scheduleCategoryId: Long,
    @Column(nullable = false)
    var calendarId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekly_goal_id")
    var id: Long? = null,
) {
}