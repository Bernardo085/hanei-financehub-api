package com.hanei.financehub.api.infrastructure.persistence.repository;

import com.hanei.financehub.api.infrastructure.persistence.document.TransactionDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionDocument, String> {
    Page<TransactionDocument> findByUserIdAndAccountId(String userId, String accountId, Pageable pageable);
}