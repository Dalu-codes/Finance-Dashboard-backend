package com.project.finance_dashboard.dto;

import java.math.BigDecimal;

public record OverviewResponse(
        BigDecimal overallBalance,
        BigDecimal totalBalance,
        BigDecimal totalExpense
) {}
