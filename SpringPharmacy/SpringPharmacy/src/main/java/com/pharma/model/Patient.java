package com.pharma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Patient{
	
	@Id
	@NotNull
	private long patientId;
	@NotNull
	@Size(min = 3, message="name should have atleast 3 characters")
	private String patientName;
	@NotNull
	private int contact;
	@NotNull
	private int age;
	@NotNull
	private String gender;
	@NotNull
	private int quantity;
	
	//private long medicineId;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(long patientId, String patientName, int contact, int age, String gender, int quantity) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.contact = contact;
		this.age = age;
		this.gender = gender;
		this.quantity = quantity;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", contact=" + contact + ", age="
				+ age + ", gender=" + gender + ", quantity=" + quantity + "]";
	}
	
	
	
	


}
