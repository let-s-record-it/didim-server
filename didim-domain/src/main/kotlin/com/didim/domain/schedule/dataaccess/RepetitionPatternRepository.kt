package com.didim.domain.schedule.dataaccess

import com.didim.domain.schedule.domain.RepetitionPattern

interface RepetitionPatternRepository {

    fun findByScheduleGroupId(scheduleGroupId: Long): RepetitionPattern?
}