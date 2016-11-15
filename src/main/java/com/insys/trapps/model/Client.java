package com.insys.trapps.model;

import java.util.Arrays;

import javax.persistence.*;;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String[] emails;

    @Column(nullable = false)
    private String address;

  
    @Column(nullable = false)
    private String[] projects;

    @Column(nullable = false)
    private String[] roles;

    @Column(nullable = false)
    private boolean msa;
   
    @Column(nullable = false)
    private String msaExpirationDate;
    
    
    
    
	public Client() {
		super();
	}



	public Client(Long id, String name, String description, String location,
			String region, String phone, String[] emails, String address,
			String[] projects, String[] roles, boolean msa, String msaExpirationDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.region = region;
		this.phone = phone;
		this.emails = emails;
		this.address = address;
		this.projects = projects;
		this.roles = roles;
		this.msa = msa;
		this.msaExpirationDate = msaExpirationDate;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String[] getEmails() {
		return emails;
	}



	public void setEmails(String[] emails) {
		this.emails = emails;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String[] getProjects() {
		return projects;
	}



	public void setProjects(String[] projects) {
		this.projects = projects;
	}



	public String[] getRoles() {
		return roles;
	}



	public void setRoles(String[] roles) {
		this.roles = roles;
	}



	public boolean isMsa() {
		return msa;
	}



	public void setMsa(boolean msa) {
		this.msa = msa;
	}



	public String getMsaExpirationDate() {
		return msaExpirationDate;
	}



	public void setMsaExpirationDate(String msaExpirationDate) {
		this.msaExpirationDate = msaExpirationDate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", description=" + description
				+ ", location=" + location + ", region=" + region + ", phone=" + phone
				+ ", emails=" + Arrays.toString(emails) + ", address=" + address
				+ ", projects=" + Arrays.toString(projects) + ", roles="
				+ Arrays.toString(roles) + ", msa=" + msa + ", msaExpirationDate="
				+ msaExpirationDate + "]";
	}
	
	
	
	
}
