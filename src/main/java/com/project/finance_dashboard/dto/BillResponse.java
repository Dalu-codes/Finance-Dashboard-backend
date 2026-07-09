package com.project.finance_dashboard.dto;

import java.math.BigDecimal;

/** dueDate is an ISO date string, e.g. "2026-06-29" */
public record BillResponse(
        String id,
        String name,
        String dueDate,
        BigDecimal amount
) {}
