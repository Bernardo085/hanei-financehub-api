package com.hanei.financehub.api.infrastructure.persistence.mapper;

import com.hanei.financehub.api.domain.model.*;
import com.hanei.financehub.api.infrastructure.persistence.document.ImportMetadataDocument;
import com.hanei.financehub.api.infrastructure.persistence.document.TransactionDocument;
import com.hanei.financehub.api.infrastructure.persistence.document.TransferDetailsDocument;

public class TransactionMapper {

    public static TransactionDocument toDocument(Transaction tx) {
        TransferDetailsDocument transferDoc = tx.getTransferDetails() != null
                ? new TransferDetailsDocument(tx.getTransferDetails().getFromAccountId(), tx.getTransferDetails().getToAccountId())
                : null;
        ImportMetadataDocument importDoc = tx.getImportMetadata() != null
                ? new ImportMetadataDocument(tx.getImportMetadata().getSource(), tx.getImportMetadata().getExternalFitId())
                : null;

        return new TransactionDocument(
                tx.getId(), tx.getUserId(), tx.getAccountId(), tx.getCategoryId(), tx.getType().name(),
                tx.getAmount(), tx.getCompetenceDate(), tx.getDescription(), tx.getStatus().name(),
                tx.isRecurrent(), transferDoc, importDoc, tx.getCreatedAt(), tx.getUpdatedAt()
        );
    }

    public static Transaction toDomain(TransactionDocument doc) {
        TransferDetails transferDetails = doc.getTransferDetails() != null
                ? new TransferDetails(doc.getTransferDetails().getFromAccountId(), doc.getTransferDetails().getToAccountId())
                : null;
        ImportMetadata importMetadata = doc.getImportMetadata() != null
                ? new ImportMetadata(doc.getImportMetadata().getSource(), doc.getImportMetadata().getExternalFitId())
                : null;

        return Transaction.reconstitute(
                doc.getId(), doc.getUserId(), doc.getAccountId(), doc.getCategoryId(),
                TransactionType.valueOf(doc.getType()), doc.getAmount(), doc.getCompetenceDate(),
                doc.getDescription(), TransactionStatus.valueOf(doc.getStatus()), doc.isRecurrent(),
                transferDetails, importMetadata, doc.getCreatedAt(), doc.getUpdatedAt()
        );
    }
}