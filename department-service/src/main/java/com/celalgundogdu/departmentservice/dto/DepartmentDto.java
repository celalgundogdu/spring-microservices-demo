package com.celalgundogdu.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Department name can not be empty")
    @NotNull(message = "Department name can not be null")
    private String departmentName;

    @NotBlank(message = "Department description can not be empty")
    @NotNull(message = "Department description can not be null")
    @Size(min = 5, max = 50, message = "Invalid description: Must be of 5-50 characters")
    private String departmentDescription;

    @NotBlank(message = "Department code can not be empty")
    @NotNull(message = "Department code can not be null")
    private String departmentCode;
}
