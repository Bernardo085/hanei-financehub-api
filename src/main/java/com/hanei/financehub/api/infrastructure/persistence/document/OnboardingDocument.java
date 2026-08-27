package com.hanei.financehub.api.infrastructure.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingDocument {
    private boolean completed;
    private String primaryGoal;
    private Instant completedAt;
}