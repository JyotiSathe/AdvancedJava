package com.siemens.view.java9to21.java16;

import com.github.javafaker.Faker;
import com.siemens.model.CompanyType;
import com.siemens.model.Corporate;
import com.siemens.model.Customer;
import com.siemens.model.FullName;

import static com.siemens.view.java8.functionalinterface.ComparatorDemo.generateAddresses;

public class PatternMatcherDemo {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Corporate corporate = Corporate.builder()
                .accountNumber(faker.number().numberBetween(10000, 1000000))
                .fullName(new FullName(faker.name().firstName(), "", faker.name().lastName()))
                .addresses(generateAddresses())
                .contactNumber(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .companyType(generateCompanyType())
                .build();

        if (corporate instanceof Customer customer) {
            System.out.println(customer.getAccountNumber());
        }
    }

    public static CompanyType generateCompanyType() {
        CompanyType[] values = CompanyType.values();
        return values[(int) (Math.random() * values.length)];
    }
}
