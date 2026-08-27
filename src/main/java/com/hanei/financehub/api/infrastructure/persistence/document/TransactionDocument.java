package com.hanei.financehub.api.infrastructure.persistence.document;

import com.hanei.financehub.api.domain.model.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Document("transactions")
@CompoundIndexes({
        @CompoundIndex(name = "user_competence_account_idx",
                def = "{'userId': 1, 'competenceDate': 1, 'accountId': 1}")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDocument {

    @Id
    private String id;
    private String userId;
    private String accountId;
    private String categoryId;
    private String type;
    private Money amount;
    private LocalDate competenceDate;
    private String description;
    private String status;
    private boolean recurrent;
    private TransferDetailsDocument transferDetails;
    private ImportMetadataDocument importMetadata;
    private Instant createdAt;
    private Instant updatedAt;
}