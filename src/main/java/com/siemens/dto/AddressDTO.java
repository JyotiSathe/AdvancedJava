package com.siemens.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Map<String, String> geo;
}
