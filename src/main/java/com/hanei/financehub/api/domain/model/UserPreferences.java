package com.hanei.financehub.api.domain.model;

public final class UserPreferences {

    private final boolean newsletterSubscribed;
    private final String currency;

    public UserPreferences(boolean newsletterSubscribed, String currency) {
        this.newsletterSubscribed = newsletterSubscribed;
        this.currency = currency;
    }

    public static UserPreferences defaults() {
        return new UserPreferences(true, "BRL");
    }

    public boolean isNewsletterSubscribed() { return newsletterSubscribed; }
    public String getCurrency() { return currency; }
}