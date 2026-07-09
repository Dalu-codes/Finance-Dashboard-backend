package com.project.finance_dashboard.dto;

import java.math.BigDecimal;

/** icon must be one of: "plane" | "home" | "gift" — the frontend maps this to an SVG icon. */
public record InvestmentPlanResponse(
        String id,
        String name,
        String icon,
        BigDecimal current,
        BigDecimal target
) {}

