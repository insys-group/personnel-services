/**
 *
 */
package com.insys.trapps.model;

public enum DocumentType {
    RESUME("Resume"),
    INSYSPROFILE("INSYS Profile");

    private String documentType;

    DocumentType(String documentType) {
        this.documentType = documentType;
    }
}
