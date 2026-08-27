package com.hanei.financehub.api.infrastructure.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDocument {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String email;

    private String passwordHash;
    private String phone;
    private String avatarUrl;
    private String authProvider;
    private OnboardingDocument onboarding;
    private UserPreferencesDocument preferences;
    private Instant createdAt;
    private Instant updatedAt;
}