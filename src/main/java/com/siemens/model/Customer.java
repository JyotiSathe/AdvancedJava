package com.siemens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    protected long accountNumber;
    protected FullName fullName;
    protected List<Address> addresses;
    protected String contactNumber;
    protected String email;
    protected String password;
}
