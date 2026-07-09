package com.project.finance_dashboard.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record CreateInvestmentPlanRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "plane|home|gift", message = "icon must be one of: plane, home, gift")
        String icon,
        @NotNull @PositiveOrZero BigDecimal current,
        @NotNull @Positive BigDecimal target
) {}
