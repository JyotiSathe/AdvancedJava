package com.siemens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullName {
    protected String firstName;
    protected String middleName;
    protected String lastName;
}
