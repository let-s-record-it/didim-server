package com.didim.dbmain.schedule.entity

import com.didim.domain.schedule.domain.RepetitionType
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
    var repetitionType: RepetitionType,
    var repetitionPeriod: Int,
    var repetitionStartDate: LocalDateTime,
    var repetitionEndDate: LocalDateTime,
    var monthOfYear: Int,
    var dayOfMonth: Int,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_group_id")
    var id: Long? = null,
) {
}