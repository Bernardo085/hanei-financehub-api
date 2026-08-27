package com.hanei.financehub.api.domain.model;

public final class ImportMetadata {

    private final String source;
    private final String externalFitId;

    public ImportMetadata(String source, String externalFitId) {
        this.source = source;
        this.externalFitId = externalFitId;
    }

    public String getSource() { return source; }
    public String getExternalFitId() { return externalFitId; }
}