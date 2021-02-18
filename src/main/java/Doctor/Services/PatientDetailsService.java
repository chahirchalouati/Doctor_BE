/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Services;

import Doctor.Entities.Address;
import Doctor.Entities.AppUser;
import Doctor.Entities.File;
import Doctor.Entities.PatientDetails;
import Doctor.Repositories.BloodTypeRepository;
import Doctor.Repositories.PatientDetailsRepository;
import Doctor.Repositories.UserRepository;
import Doctor.Utilities.Projections.PatientDetailsPro;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
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
public class PatientDetailsService {

    private final UserRepository userRepository;
    private final PatientDetailsRepository detailsRepository;
    private final FileService fileService;
    private final BloodTypeRepository bloodTypeRepository;

    /**
     * creation of patient details object for patient
     *
     * @param patientDetails
     * @param principal
     * @return saved patient details object
     * @throws ParseException if date given is invalid
     */
    @Transactional
    public ResponseEntity<?> create(PatientDetailsPro patientDetails, Principal principal) {

        AppUser user = userRepository.findByEmail(principal.getName());

        if (patientDetails.getFile() != null) {
            File storeFile = fileService.storeFile(patientDetails.getFile(), user.getEmail());

            PatientDetails details = new PatientDetails();
            // create address
            Address address = new Address(null,
                    patientDetails.getCity(),
                    patientDetails.getProvince(),
                    patientDetails.getAvenue(),
                    patientDetails.getBuilding(),
                    patientDetails.getCAP(),
                    new Date());

            //adding data to patient details object
            details.setAddress(address);
            details.setAvatarUrl(storeFile.getURL());
            details.setBirthdate(patientDetails.getBirthDate());
            details.setGender(patientDetails.getGender());
            details.setHeight(patientDetails.getHeight());
            details.setWeight(patientDetails.getWeight());
            details.setBloodType(bloodTypeRepository.findByType(patientDetails.getBloodType()));
            details.setTaxCode(patientDetails.getTaxCode());
            details.setNumberPhone(patientDetails.getNumberPhone());
            details.setPatient(user);
            details.setCreatedAt(new Date());

            PatientDetails save = detailsRepository.save(details);

            user.setNew(false);
            user.setAvatarUrl(storeFile.getURL());
            userRepository.save(user);

            return new ResponseEntity<>(save, HttpStatus.CREATED);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResponseEntity update(PatientDetails payload, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
