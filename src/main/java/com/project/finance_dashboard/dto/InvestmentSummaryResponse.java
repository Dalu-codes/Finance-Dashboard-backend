package com.project.finance_dashboard.dto;

import java.math.BigDecimal;
import java.util.List;

public record InvestmentSummaryResponse(
        BigDecimal totalSavings,
        List<InvestmentPlanResponse> plans
) {}

