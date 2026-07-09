package com.project.finance_dashboard.controller;

import com.project.finance_dashboard.dto.CreateBillRequest;
import com.project.finance_dashboard.dto.CreateInvestmentPlanRequest;
import com.project.finance_dashboard.dto.FinanceDashboardResponse;
import com.project.finance_dashboard.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/dashboard")
    public FinanceDashboardResponse getDashboard(
            @RequestParam(defaultValue = "This week") String period) {
        return financeService.buildDashboard(period);
    }

    @PostMapping("/bills")
    public ResponseEntity<Void> addBill(@Valid @RequestBody CreateBillRequest request) {
        financeService.addBill(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/investments")
    public ResponseEntity<Void> addInvestmentPlan(@Valid @RequestBody CreateInvestmentPlanRequest request) {
        financeService.addInvestmentPlan(request);
        return ResponseEntity.noContent().build();
    }
}

