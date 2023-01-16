package com.celalgundogdu.employeeservice.dto;

import com.celalgundogdu.employeeservice.annotations.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "First name can not be empty")
    @NotNull(message = "First name can not be null")
    private String firstName;

    @NotBlank(message = "Last name can not be empty")
    @NotNull(message = "Last name can not be null")
    private String lastName;

    @Email(message = "Invalid email")
    @UniqueEmail
    private String email;

    private String departmentCode;
}
