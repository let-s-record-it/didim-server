package com.didim.dbmain.alarm.entity

import com.didim.domain.alarm.domain.AlarmLog
import com.didim.domain.alarm.domain.AlarmType
import jakarta.persistence.*

@Table(name = "alarm_log")
@Entity
class AlarmLogEntity(
    @Column(nullable = false)
    var alarmType: AlarmType,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    var senderKey: String,

    @Column(nullable = false)
    var receiverKey: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_log_id")
    var id: Long? = null,
) {
    companion object {
        fun from(alarmLog: AlarmLog) = AlarmLogEntity(
            alarmType = alarmLog.alarmType,
            content = alarmLog.content,
            senderKey = alarmLog.senderKey,
            receiverKey = alarmLog.receiverKey,
        )
    }
}