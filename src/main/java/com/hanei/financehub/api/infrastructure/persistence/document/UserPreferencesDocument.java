package com.hanei.financehub.api.infrastructure.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferencesDocument {
    private boolean newsletterSubscribed;
    private String currency;
}