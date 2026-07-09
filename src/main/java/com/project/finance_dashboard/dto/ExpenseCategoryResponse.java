package com.project.finance_dashboard.dto;

import java.math.BigDecimal;

public record ExpenseCategoryResponse(
        String id,
        String name,
        BigDecimal amount,
        String colorHex
) {}
