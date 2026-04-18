package com.didim.dbmain.schedule.entity

import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "repetition_pattern")
@Entity
class RepetitionPatternEntity(
    @Column(nullable = false)
    var repetitionType: RepetitionType,
    @Column(nullable = false)
    var repetitionPeriod: Int,
    @Column(nullable = false)
    var repetitionStartDate: LocalDateTime,
    @Column(nullable = false)
    var repetitionEndDate: LocalDateTime,
    @Column
    var monthOfYear: Int? = null,
    @Column
    var dayOfMonth: Int? = null,
    @Column
    var weekNumber: WeekNumber? = null,
    @Column
    var weekday: Weekday? = null,
    @Column
    var weekdayBit: Int? = null,
    @Column(nullable = false)
    var scheduleGroupId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_group_id")
    var id: Long? = null,
) {
}