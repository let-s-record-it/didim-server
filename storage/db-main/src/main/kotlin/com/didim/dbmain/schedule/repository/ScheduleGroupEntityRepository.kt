package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleGroupEntity
import com.didim.domain.schedule.dataaccess.ScheduleGroupRepository
import com.didim.domain.schedule.domain.ScheduleGroup
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class ScheduleGroupEntityRepository(
    private val scheduleGroupJpaRepository: ScheduleGroupJpaRepository,
    private val scheduleGroupCustomRepository: ScheduleGroupCustomRepository,
) : ScheduleGroupRepository {

    override fun save(scheduleGroup: ScheduleGroup): Long =
        scheduleGroupJpaRepository.save(ScheduleGroupEntity.from(scheduleGroup)).id!!

    override fun deleteById(id: Long) {
        scheduleGroupCustomRepository.deleteById(id)
    }

}