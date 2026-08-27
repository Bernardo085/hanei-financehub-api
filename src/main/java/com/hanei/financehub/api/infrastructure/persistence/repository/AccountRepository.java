package com.hanei.financehub.api.infrastructure.persistence.repository;

import com.hanei.financehub.api.infrastructure.persistence.document.AccountDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountDocument, String> {
    List<AccountDocument> findByUserId(String userId);
    List<AccountDocument> findByUserIdAndArchivedFalse(String userId);
}