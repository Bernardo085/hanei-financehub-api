package com.hanei.financehub.api.infrastructure.persistence.document;

import com.hanei.financehub.api.domain.model.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDocument {

    @Id
    private String id;

    @Indexed
    private String userId;

    private String name;
    private String type;
    private String colorHex;
    private Money initialBalance;
    private Money currentBalance;
    private Money targetGoal;
    private boolean archived;
    private Instant createdAt;
    private Instant updatedAt;
}