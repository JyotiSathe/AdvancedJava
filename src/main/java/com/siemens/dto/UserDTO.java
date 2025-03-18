package com.siemens.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private AddressDTO address;
    private String phone;
    private String website;
    private Map<String, String> company;
}
