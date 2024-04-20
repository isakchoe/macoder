package com.macoder.styling.consulting.service

import com.macoder.styling.consulting.dto.OrderConsultingRequest
import com.macoder.styling.consulting.dto.OrderConsultingResponse
import com.macoder.styling.consulting.persistence.ConsultingRepository
import com.macoder.styling.common.entity.Consulting
import com.macoder.styling.util.flushOrThrow
import org.springframework.stereotype.Service


@Service
class ConsultingService(private val consultingRepository: ConsultingRepository) {
    fun orderConsulting(request: OrderConsultingRequest): OrderConsultingResponse {
        val consulting = Consulting.from(request)

        return OrderConsultingResponse.from(consultingRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) {
            save(consulting)
        })
    }



//    fun deliverConsulting(request: DeliverConsultingRequest): DeliverConsultingResponse {
//
//        val consulting = Consulting.from(request)
//
//        return DeliverConsultingResponse.from(consultingRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) {
//            save(consulting)
//    }
}
