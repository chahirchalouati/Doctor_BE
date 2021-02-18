/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Services;

import Doctor.Entities.AppUser;
import Doctor.Repositories.PatientDetailsRepository;
import Doctor.Repositories.UserRepository;
import Doctor.Utilities.Projections.AdminProfilePro;
import Doctor.Utilities.Projections.DoctorProfilePro;
import Doctor.Utilities.Projections.PatientProfilePro;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class ProfileService {

    private final PatientProfilePro patientProfilePro;
    private final DoctorProfilePro DoctorProfilePro;
    private final AdminProfilePro adminProfilePro;
    private final UserRepository userRepository;
    private final PatientDetailsRepository patientDetailsRepository;

    /**
     * find Patient Profile details
     *
     * @param principal
     * @param email
     * @return patient profile Projection with AppUser and Patient details
     */
    @Transactional
    public ResponseEntity<?> findPatientProfile(Principal principal, String email) {

        AppUser user = userRepository.findByEmail(principal.getName());
        patientProfilePro.setUser(user);
        patientProfilePro.setPatientDetails(// find lastest patient details
                patientDetailsRepository.findByPatient_idOrderByCreatedAtDesc(user.getId())
                        .stream()
                        .findFirst()
                        .get());
        return new ResponseEntity<>(patientProfilePro, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findDoctorProfile(Principal principal, String email) {

        return new ResponseEntity<>(patientProfilePro, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findAdminProfile(Principal principal, String email) {
        return new ResponseEntity<>(patientProfilePro, HttpStatus.NOT_FOUND);
    }

}
