package com.project.finance_dashboard.dto;

public record UserSummaryResponse(
        String firstName,
        String role,
        String avatarInitial
) {}
