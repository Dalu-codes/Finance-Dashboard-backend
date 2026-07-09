package com.project.finance_dashboard.dto;

import java.math.BigDecimal;
import java.util.List;

public record ExpenseSummaryResponse(
        BigDecimal total,
        double percentChangeVsLastWeek,
        List<ExpenseCategoryResponse> categories
) {}

