package com.macoder.styling.consulting.service

import com.macoder.styling.authentication.persistence.MemberRepository
import com.macoder.styling.authentication.persistence.StylistRepository
import com.macoder.styling.consulting.dto.ConsultingOrderRequest
import com.macoder.styling.consulting.dto.OrderConsultingResponse
import com.macoder.styling.consulting.persistence.ConsultingRepository
import com.macoder.styling.common.entity.Consulting
import com.macoder.styling.util.flushOrThrow
import org.springframework.stereotype.Service


@Service
class ConsultingService(
    private val consultingRepository: ConsultingRepository,
    private val stylistRepository: StylistRepository,
    private val memberRepository: MemberRepository
) {
    fun orderConsulting(request: ConsultingOrderRequest): OrderConsultingResponse {

        val stylist = stylistRepository.findById(request.stylistId ?: 1).orElseThrow { NoSuchElementException("Stylist not found") }
        val member = memberRepository.findById(request.memberId).orElseThrow { NoSuchElementException("Member not found") }

        val consulting = Consulting.from(stylist, member, request.consultingRequires)

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
