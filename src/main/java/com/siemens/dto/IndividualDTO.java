package com.siemens.dto;

import com.siemens.model.FullName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDTO {
    private FullName fullName;
    private LocalDate dateOfBirth;
}
