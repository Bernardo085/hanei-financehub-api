package com.hanei.financehub.api.infrastructure.persistence.mapper;

import com.hanei.financehub.api.domain.model.Account;
import com.hanei.financehub.api.domain.model.AccountType;
import com.hanei.financehub.api.infrastructure.persistence.document.AccountDocument;

public class AccountMapper {

    public static AccountDocument toDocument(Account account) {
        return new AccountDocument(
                account.getId(), account.getUserId(), account.getName(), account.getType().name(),
                account.getColorHex(), account.getInitialBalance(), account.getCurrentBalance(),
                account.getTargetGoal(), account.isArchived(), account.getCreatedAt(), account.getUpdatedAt()
        );
    }

    public static Account toDomain(AccountDocument doc) {
        return Account.reconstitute(
                doc.getId(), doc.getUserId(), doc.getName(), AccountType.valueOf(doc.getType()),
                doc.getColorHex(), doc.getInitialBalance(), doc.getCurrentBalance(), doc.getTargetGoal(),
                doc.isArchived(), doc.getCreatedAt(), doc.getUpdatedAt()
        );
    }
}