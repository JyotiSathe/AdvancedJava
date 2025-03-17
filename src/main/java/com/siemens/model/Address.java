package com.siemens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private long addressNumber;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
