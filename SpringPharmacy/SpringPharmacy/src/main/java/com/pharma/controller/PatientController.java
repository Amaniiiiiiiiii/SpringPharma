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

import com.pharma.dao.PatientDao;
import com.pharma.exception.PatientNotFoundException;
import com.pharma.model.Patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientDao patientDAO;

    @Autowired
    public PatientController(PatientDao patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patient = patientDAO.getAllPatients();
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) throws PatientNotFoundException{
        Patient patient = patientDAO.getPatientById(patientId);
        if(patient == null)
        	throw new PatientNotFoundException("Patient" + patientId+ "not found");
        return ResponseEntity.ok(patient);
        
        
//        if (task != null) {
//            return ResponseEntity.ok(task);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        Patient createdPatient = patientDAO.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@Valid @PathVariable Long patientId, @RequestBody Patient patient) {
        Patient existingPatient = patientDAO.getPatientById(patientId);
        if (existingPatient != null) {
        	existingPatient.setPatientName(patient.getPatientName());
            Patient updatedPatient = patientDAO.updatePatient(existingPatient);
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientid) {
        Patient patient = patientDAO.getPatientById(patientid);
        if (patient != null) {
        	patientDAO.deletePatient(patient);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
