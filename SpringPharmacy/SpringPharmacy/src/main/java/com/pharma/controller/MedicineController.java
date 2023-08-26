package com.pharma.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma.dao.MedicineDao;
import com.pharma.exception.MedicineNotFoundException;
import com.pharma.model.Medicine;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineDao medicineDAO;

    @Autowired
    public MedicineController(MedicineDao medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicine = medicineDAO.getAllMedicines();
        return ResponseEntity.ok(medicine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long medicineId) throws MedicineNotFoundException{
        Medicine medicine = medicineDAO.getMedicineById(medicineId);
        if(medicine == null)
        	throw new MedicineNotFoundException("Medicine" + medicineId+ "not found");
        return ResponseEntity.ok(medicine);
        
        
//        if (task != null) {
//            return ResponseEntity.ok(task);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Medicine> createMedicine(@Valid @RequestBody Medicine medicine) {
        Medicine createdMedicine = medicineDAO.createMedicine(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@Valid @PathVariable Long medicineId, @RequestBody Medicine medicine) {
        Medicine existingMedicine = medicineDAO.getMedicineById(medicineId);
        if (existingMedicine != null) {
        	existingMedicine.setMedicineName(medicine.getMedicineName());
            Medicine updatedMedicine = medicineDAO.updateMedicine(existingMedicine);
            return ResponseEntity.ok(updatedMedicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        Medicine medicine = medicineDAO.getMedicineById(id);
        if (medicine != null) {
        	medicineDAO.deleteMedicine(medicine);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
