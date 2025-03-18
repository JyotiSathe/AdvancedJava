package com.siemens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAggregator {
    private BigDecimal totalCost = BigDecimal.ZERO;
}
