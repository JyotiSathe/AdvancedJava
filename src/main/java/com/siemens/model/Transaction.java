package com.siemens.model;

import java.time.LocalDate;

public record Transaction(long amount, LocalDate timeStamp, String sender, String receiver) {
}
