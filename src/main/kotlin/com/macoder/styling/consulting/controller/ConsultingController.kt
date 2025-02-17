package com.macoder.styling.consulting.controller

import com.macoder.styling.authentication.security.StylistAuthorize
import com.macoder.styling.authentication.security.UserAuthorize
import com.macoder.styling.consulting.dto.ConsultingOrderRequest
import com.macoder.styling.common.dto.ApiResponse
import com.macoder.styling.consulting.dto.ConsultingWriteRequest
import com.macoder.styling.consulting.service.ConsultingService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RequestMapping("/consulting")
@Tag(name = "스타일 컨설팅 API")
@RestController
class ConsultingController(private val consultingService: ConsultingService) {

    @Operation(summary = "컨설팅 요청")
    @UserAuthorize
    @PostMapping("/order", consumes = ["multipart/form-data"])
    fun orderConsulting(
        @RequestParam("file") file: MultipartFile? = null,
        @RequestPart("request") request: ConsultingOrderRequest
    ) = ApiResponse.success(consultingService.orderConsulting(file, request))


    @Operation(summary = "컨설팅 응답")
    @StylistAuthorize
    @PostMapping("/deliver", consumes = ["multipart/form-data"])
    fun deliverConsulting(
        @RequestParam("file") file: MultipartFile? = null,
        @RequestPart("request") request: ConsultingWriteRequest) =
        ApiResponse.success(consultingService.writeConsulting(file, request))


    @Operation(summary = "컨설팅 리스트 조회")
    @StylistAuthorize
    @GetMapping()
    fun getConsultingList(@AuthenticationPrincipal user: User): ApiResponse {
        return ApiResponse.success(consultingService.getConsultingList(user.username.toLong()))
    }

}
