package com.project.finance_dashboard.service;

import com.project.finance_dashboard.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Holds all dashboard data in memory. Swap this out for a repository-backed
 * implementation later (JPA, Mongo, etc.) without touching the controller —
 * that's the point of keeping the service as the seam between them.
 */
@Service
public class FinanceService {

    // Mutable so addBill() can append to it; everything else is static demo data for now.
    private final List<BillResponse> bills = new CopyOnWriteArrayList<>(List.of(
            new BillResponse("gym-1", "Gym membership", "2026-06-29", new BigDecimal("70000")),
            new BillResponse("gym-2", "Gym membership", "2026-06-29", new BigDecimal("70000")),
            new BillResponse("gym-3", "Gym membership", "2026-06-29", new BigDecimal("70000"))
    ));

    // Mutable so addInvestmentPlan() can append to it.
    private final List<InvestmentPlanResponse> investmentPlans = new CopyOnWriteArrayList<>(List.of(
            new InvestmentPlanResponse("trip-maldives", "Trip to Maldives", "plane",
                    new BigDecimal("300000"), new BigDecimal("400000")),
            new InvestmentPlanResponse("new-apartment", "New Apartment", "home",
                    new BigDecimal("2000000"), new BigDecimal("5000000")),
            new InvestmentPlanResponse("birthday", "Birthday", "gift",
                    new BigDecimal("50000"), new BigDecimal("200000"))
    ));

    // Guards totalSavings, which grows whenever a plan is added with a starting balance.
    private volatile BigDecimal totalSavings = new BigDecimal("500000");

    public FinanceDashboardResponse buildDashboard(String period) {
        return new FinanceDashboardResponse(
                user(),
                overview(),
                cashFlow(period),
                investments(),
                bills,
                expenses()
        );
    }

    public void addBill(CreateBillRequest request) {
        bills.add(new BillResponse(
                UUID.randomUUID().toString(),
                request.name(),
                request.dueDate(),
                request.amount()
        ));
    }

    public synchronized void addInvestmentPlan(CreateInvestmentPlanRequest request) {
        investmentPlans.add(new InvestmentPlanResponse(
                UUID.randomUUID().toString(),
                request.name(),
                request.icon(),
                request.current(),
                request.target()
        ));
        totalSavings = totalSavings.add(request.current());
    }

    private UserSummaryResponse user() {
        return new UserSummaryResponse("John", "Software engineer", "J");
    }

    private OverviewResponse overview() {
        BigDecimal hundredMillion = new BigDecimal("100000000");
        return new OverviewResponse(hundredMillion, hundredMillion, hundredMillion);
    }

    private List<CashFlowPointResponse> cashFlow(String period) {
        // period is currently accepted but not varied — hook real per-period
        // aggregation in here once there's a data source to query.
        return List.of(
                new CashFlowPointResponse("Monday", new BigDecimal("40000"), new BigDecimal("85000")),
                new CashFlowPointResponse("Tuesday", new BigDecimal("25000"), new BigDecimal("34000")),
                new CashFlowPointResponse("Wednesday", new BigDecimal("50000"), new BigDecimal("58000")),
                new CashFlowPointResponse("Thursday", new BigDecimal("90000"), new BigDecimal("26000")),
                new CashFlowPointResponse("Friday", new BigDecimal("8000"), new BigDecimal("98000")),
                new CashFlowPointResponse("Saturday", new BigDecimal("82000"), new BigDecimal("25000")),
                new CashFlowPointResponse("Sunday", new BigDecimal("80000"), new BigDecimal("24000"))
        );
    }

    private InvestmentSummaryResponse investments() {
        return new InvestmentSummaryResponse(totalSavings, investmentPlans);
    }

    private ExpenseSummaryResponse expenses() {
        List<ExpenseCategoryResponse> categories = List.of(
                new ExpenseCategoryResponse("bus", "Bus Transport", new BigDecimal("20000"), "#272727"),
                new ExpenseCategoryResponse("groceries", "Groceries", new BigDecimal("15000"), "#272727"),
                new ExpenseCategoryResponse("spa", "Spa treatment", new BigDecimal("70000"), "#272727"),
                new ExpenseCategoryResponse("shopping", "Shopping", new BigDecimal("100000"), "#272727")
        );
        return new ExpenseSummaryResponse(new BigDecimal("205000"), -20.0, categories);
    }
}
