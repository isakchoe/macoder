package com.macoder.styling.consulting.controller

import com.macoder.styling.authentication.security.StylistAuthorize
import com.macoder.styling.authentication.security.UserAuthorize
import com.macoder.styling.consulting.dto.ConsultingOrderRequest
import com.macoder.styling.common.dto.ApiResponse
import com.macoder.styling.consulting.dto.ConsultingWriteRequest
import com.macoder.styling.consulting.service.ConsultingService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/consulting")
@Tag(name = "스타일 컨설팅 API")
@RestController
class ConsultingController(private val consultingService: ConsultingService) {

    @Operation(summary = "컨설팅 요청")
    @UserAuthorize
    @PostMapping("/order")
    fun orderConsulting(@RequestBody request: ConsultingOrderRequest) = ApiResponse.success(consultingService.orderConsulting(request))

    @Operation(summary = "컨설팅 응답")
    @StylistAuthorize
    @PostMapping("/deliver")
    fun deliverConsulting(@RequestBody request: ConsultingWriteRequest) =
        ApiResponse.success(consultingService.writeConsulting(request))

}
