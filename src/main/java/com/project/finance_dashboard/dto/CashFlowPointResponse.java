package com.project.finance_dashboard.dto;

import java.math.BigDecimal;

public record CashFlowPointResponse(
        String day,
        BigDecimal expense,
        BigDecimal savings
) {}

