package com.hanei.financehub.api.infrastructure.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDetailsDocument {
    private String fromAccountId;
    private String toAccountId;
}