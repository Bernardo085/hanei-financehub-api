package com.hanei.financehub.api.domain.model;

import java.time.Instant;

public final class Onboarding {

    private final boolean completed;
    private final PrimaryGoal primaryGoal;
    private final Instant completedAt;

    private Onboarding(boolean completed, PrimaryGoal primaryGoal, Instant completedAt) {
        this.completed = completed;
        this.primaryGoal = primaryGoal;
        this.completedAt = completedAt;
    }

    public static Onboarding notStarted() {
        return new Onboarding(false, null, null);
    }

    public Onboarding complete(PrimaryGoal primaryGoal) {
        return new Onboarding(true, primaryGoal, Instant.now());
    }

    public boolean isCompleted() { return completed; }
    public PrimaryGoal getPrimaryGoal() { return primaryGoal; }
    public Instant getCompletedAt() { return completedAt; }
}