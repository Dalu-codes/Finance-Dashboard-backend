package com.project.finance_dashboard.dto;

import java.util.List;

/** Full payload returned by GET /api/finance/dashboard */
public record FinanceDashboardResponse(
        UserSummaryResponse user,
        OverviewResponse overview,
        List<CashFlowPointResponse> cashFlow,
        InvestmentSummaryResponse investments,
        List<BillResponse> bills,
        ExpenseSummaryResponse expenses
) {}



