package com.didim.dbmain.alarm.repository

import com.didim.dbmain.alarm.entity.AlarmLogEntity
import com.didim.domain.alarm.dataaccess.AlarmLogRepository
import com.didim.domain.alarm.domain.AlarmLog
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class AlarmLogEntityRepository(
    private val alarmLogJpaRepository: AlarmLogJpaRepository,
) : AlarmLogRepository {

    override fun save(alarmLog: AlarmLog): Long = alarmLogJpaRepository.save(AlarmLogEntity.from(alarmLog)).id!!

}