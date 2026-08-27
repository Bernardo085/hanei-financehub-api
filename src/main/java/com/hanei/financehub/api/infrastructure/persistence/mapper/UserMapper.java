package com.hanei.financehub.api.infrastructure.persistence.mapper;

import com.hanei.financehub.api.domain.model.*;
import com.hanei.financehub.api.infrastructure.persistence.document.OnboardingDocument;
import com.hanei.financehub.api.infrastructure.persistence.document.UserDocument;
import com.hanei.financehub.api.infrastructure.persistence.document.UserPreferencesDocument;

public class UserMapper {

    public static UserDocument toDocument(User user) {
        OnboardingDocument onboardingDoc = new OnboardingDocument(
                user.getOnboarding().isCompleted(),
                user.getOnboarding().getPrimaryGoal() != null ? user.getOnboarding().getPrimaryGoal().name() : null,
                user.getOnboarding().getCompletedAt()
        );
        UserPreferencesDocument preferencesDoc = new UserPreferencesDocument(
                user.getPreferences().isNewsletterSubscribed(),
                user.getPreferences().getCurrency()
        );

        return new UserDocument(
                user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPasswordHash(), user.getPhone(), user.getAvatarUrl(),
                user.getAuthProvider().name(), onboardingDoc, preferencesDoc,
                user.getCreatedAt(), user.getUpdatedAt()
        );
    }

    public static User toDomain(UserDocument doc) {
        Onboarding onboarding = Onboarding.reconstitute(
                doc.getOnboarding().isCompleted(),
                doc.getOnboarding().getPrimaryGoal() != null ? PrimaryGoal.valueOf(doc.getOnboarding().getPrimaryGoal()) : null,
                doc.getOnboarding().getCompletedAt()
        );
        UserPreferences preferences = new UserPreferences(
                doc.getPreferences().isNewsletterSubscribed(),
                doc.getPreferences().getCurrency()
        );

        return User.reconstitute(
                doc.getId(), doc.getFirstName(), doc.getLastName(), doc.getEmail(),
                doc.getPasswordHash(), doc.getPhone(), doc.getAvatarUrl(),
                AuthProvider.valueOf(doc.getAuthProvider()), onboarding, preferences,
                doc.getCreatedAt(), doc.getUpdatedAt()
        );
    }
}