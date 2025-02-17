package com.macoder.styling.authentication.controller

import com.macoder.styling.common.dto.ApiResponse
import com.macoder.styling.authentication.dto.MemberUpdateRequest
import com.macoder.styling.authentication.security.UserAuthorize
import com.macoder.styling.authentication.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@Tag(name = "로그인 후 사용할 수 있는 API")
@UserAuthorize
@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {
    @Operation(summary = "회원 정보 조회")
    @GetMapping
    fun getMemberInfo(@AuthenticationPrincipal user: User) = ApiResponse.success(memberService.getMemberInfo(user.username.toLong()))

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    fun deleteMember(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.deleteMember(user.username.toLong()))

    @Operation(summary = "회원 정보 수정")
    @PutMapping
    fun updateMember(@AuthenticationPrincipal user: User, @RequestBody request: MemberUpdateRequest) =
        ApiResponse.success(memberService.updateMember(user.username.toLong(), request))


    @Operation(summary = "스타일리스트 자격 신청")
    @PostMapping()
    fun updateMemberToStylist(@AuthenticationPrincipal user: User, @RequestBody request: MemberUpdateRequest) =
        ApiResponse.success(memberService.updateMemberToStylist(user.username.toLong(), request))
}
