package com.hanei.financehub.api.domain.model;

import com.hanei.financehub.api.domain.exception.BusinessRuleException;

import java.time.Instant;
import java.util.Objects;

public class User {

    private final String id;
    private String firstName;
    private String lastName;
    private final String email;
    private String passwordHash;
    private String phone;
    private String avatarUrl;
    private final AuthProvider authProvider;
    private Onboarding onboarding;
    private UserPreferences preferences;
    private final Instant createdAt;
    private Instant updatedAt;

    private User(String id, String firstName, String lastName, String email, String passwordHash,
                 String phone, AuthProvider authProvider, Instant createdAt) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName, "firstName nao pode ser nulo");
        this.lastName = Objects.requireNonNull(lastName, "lastName nao pode ser nulo");
        this.email = Objects.requireNonNull(email, "email nao pode ser nulo").toLowerCase();
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.authProvider = authProvider;
        this.onboarding = Onboarding.notStarted();
        this.preferences = UserPreferences.defaults();
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public static User registerLocal(String firstName, String lastName, String email, String phone, String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new BusinessRuleException("Cadastro local exige uma senha valida.");
        }
        return new User(null, firstName, lastName, email, passwordHash, phone, AuthProvider.LOCAL, Instant.now());
    }

    public static User registerWithGoogle(String firstName, String lastName, String email) {
        return new User(null, firstName, lastName, email, null, null, AuthProvider.GOOGLE, Instant.now());
    }

    public static User reconstitute(String id, String firstName, String lastName, String email, String passwordHash,
                                    String phone, String avatarUrl, AuthProvider authProvider, Onboarding onboarding,
                                    UserPreferences preferences, Instant createdAt, Instant updatedAt) {
        User user = new User(id, firstName, lastName, email, passwordHash, phone, authProvider, createdAt);
        user.avatarUrl = avatarUrl;
        user.onboarding = onboarding;
        user.preferences = preferences;
        user.updatedAt = updatedAt;
        return user;
    }

    public void completeOnboarding(PrimaryGoal primaryGoal) {
        this.onboarding = this.onboarding.complete(primaryGoal);
        touch();
    }

    public void changePassword(String newPasswordHash) {
        if (authProvider != AuthProvider.LOCAL) {
            throw new BusinessRuleException("Usuarios autenticados via Google nao possuem senha local.");
        }
        this.passwordHash = Objects.requireNonNull(newPasswordHash);
        touch();
    }

    public void updateProfile(String firstName, String lastName, String phone, String avatarUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        touch();
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getPhone() { return phone; }
    public String getAvatarUrl() { return avatarUrl; }
    public AuthProvider getAuthProvider() { return authProvider; }
    public Onboarding getOnboarding() { return onboarding; }
    public UserPreferences getPreferences() { return preferences; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}