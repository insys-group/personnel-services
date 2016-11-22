package com.insys.trapps.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author Brad Starkenberg
 */
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private DocumentType type;
    @Enumerated(EnumType.STRING)
    private DocumentFormat format;
    private String description;
    @Lob
    private byte[] document;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the type
     */
    public DocumentType getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(DocumentType type) {
	this.type = type;
    }

    /**
     * @return the format
     */
    public DocumentFormat getFormat() {
	return format;
    }

    /**
     * @param format
     *            the format to set
     */
    public void setFormat(DocumentFormat format) {
	this.format = format;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return the document
     */
    public byte[] getDocument() {
	return document;
    }

    /**
     * @param document
     *            the document to set
     */
    public void setDocument(byte[] document) {
	this.document = document;
    }

}
