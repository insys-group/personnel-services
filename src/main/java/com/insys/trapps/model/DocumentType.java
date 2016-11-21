package com.insys.trapps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Brad Starkenberg
 */
@Entity
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String docType;

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
     * @return the documentType
     */
    public String getDocumentType() {
	return docType;
    }

    /**
     * @param documentType
     *            the documentType to set
     */
    public void setDocumentType(String documentType) {
	this.docType = documentType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return new HashCodeBuilder(33, 7).append(docType).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (obj == this)
	    return true;
	if (obj.getClass() != getClass())
	    return false;
	DocumentType rhs = (DocumentType) obj;
	return new EqualsBuilder().append(docType, rhs.getDocumentType()).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

}
