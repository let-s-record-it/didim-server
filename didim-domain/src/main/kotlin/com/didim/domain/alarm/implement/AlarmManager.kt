package com.didim.domain.alarm.implement

import com.didim.domain.alarm.dataaccess.AlarmLogRepository
import com.didim.domain.alarm.domain.AlarmLog
import com.didim.domain.alarm.domain.AlarmMessage
import com.didim.domain.support.JsonSerializer
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class AlarmManager(
    private val alarmLogRepository: AlarmLogRepository,
    private val jsonSerializer: JsonSerializer,
) {

    fun <T> send(senderKey: String, receiverKey: String, message: AlarmMessage<T>) {
        alarmLogRepository.save(AlarmLog(
            alarmType = message.type,
            content = jsonSerializer.serialize(message.content),
            senderKey = senderKey,
            receiverKey = receiverKey,
        ))
    }
}