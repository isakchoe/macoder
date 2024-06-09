package com.macoder.styling.consulting.service

import com.macoder.styling.authentication.persistence.MemberRepository
import com.macoder.styling.authentication.persistence.StylistRepository
import com.macoder.styling.consulting.dto.ConsultingOrderRequest
import com.macoder.styling.consulting.dto.ConsultingOrderResponse
import com.macoder.styling.consulting.persistence.ConsultingRepository
import com.macoder.styling.common.entity.Consulting
import com.macoder.styling.common.entity.ConsultingImage
import com.macoder.styling.consulting.dto.ConsultingListResponse
import com.macoder.styling.consulting.dto.ConsultingWriteRequest
import com.macoder.styling.consulting.dto.ConsultingWriteResponse
import com.macoder.styling.consulting.persistence.ConsultingImageRepository
import com.macoder.styling.util.flushOrThrow
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.UUID
import kotlin.jvm.optionals.getOrNull


@Service
class ConsultingService(
    private val consultingRepository: ConsultingRepository,
    private val stylistRepository: StylistRepository,
    private val memberRepository: MemberRepository,
    private val consultingImageRepository: ConsultingImageRepository,
    @Value("\${file.upload-dir-request}") private val uploadDir: String
) {

    @Transactional
    fun orderConsulting(file: MultipartFile, request: ConsultingOrderRequest): ConsultingOrderResponse {
        val stylist = stylistRepository.findById(request.stylistId ?: 1)
            .orElseThrow { NoSuchElementException("Stylist not found") }

        // todo 토큰 파싱으로 대체... 필요없는 부분
        val member =
            memberRepository.findById(request.memberId).orElseThrow { NoSuchElementException("Member not found") }

        println(uploadDir)
        val fileName = UUID.randomUUID().toString() + "_" + file.originalFilename
        val filePath = saveFileToLocalFileSystem(file, fileName)
        val image = ConsultingImage(
            contentType = file.contentType ?: "unknown",
            path = filePath.toString(),
            title = request.memberId.toString(),
            description = request.consultingRequires
        )

        println("---")
        consultingImageRepository.save(image)

        val consulting = Consulting.of(stylist, member, request.consultingRequires)

        return ConsultingOrderResponse.from(consultingRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) {
            save(consulting)
        })
    }


    private fun saveFileToLocalFileSystem(file: MultipartFile, fileName: String): Path {
        val targetLocation = Paths.get(uploadDir).resolve(fileName)
        println(targetLocation)
        try {
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
        } catch (ex: IOException) {
            throw RuntimeException("Could not store file $fileName. Please try again!", ex)
        }
        return targetLocation
    }


    fun writeConsulting(request: ConsultingWriteRequest): ConsultingWriteResponse {
        // consulting patch by id
        val consulting = consultingRepository.findById(request.consultingId).getOrNull()
            ?: throw NoSuchElementException("Consulting not found")

        // contesnts 수정
        consulting.writeConsultingContents(request.consultingContents)

        return ConsultingWriteResponse.from(consulting)
    }


    fun getConsultingList(stylistId: Long): ConsultingListResponse {
        val stylist = stylistRepository.findById(stylistId).getOrNull()?:
            throw NoSuchElementException("Not found stylist")
        val consultingList = consultingRepository.findAllByStylist(stylist)
        return ConsultingListResponse.from(consultingList)
    }
}
