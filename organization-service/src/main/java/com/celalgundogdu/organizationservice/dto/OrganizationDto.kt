package com.celalgundogdu.organizationservice.dto

import com.celalgundogdu.organizationservice.annotations.UniqueCode
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class OrganizationDto @JvmOverloads constructor(

    val id: Long? = 0,

    @field:NotBlank(message = "Organization name can not be empty")
    val organizationName: String,

    val organizationDescription: String? = "",

    @field:UniqueCode
    @field:NotBlank(message = "Organization code can not be empty")
    val organizationCode: String?,

    val createdDate: LocalDateTime? = null
)
