package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();

        patientDTO.setId(patientDTO.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());

        return patientDTO;
    }
}
