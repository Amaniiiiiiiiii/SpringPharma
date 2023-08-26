package com.pharma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Medicine {

	@Id
	@NotNull
	private long medicineId;
	@NotNull
	private String medicineName;
	@NotNull
	private int price;
	@NotNull
	private int quantity;
	
	
	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Medicine(long medicineId, String medicineName, int price, int quantity) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.price = price;
		this.quantity = quantity;
	}
	public long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Medication [medicineId=" + medicineId + ", medicineName=" + medicineName + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
	
	
	
}
