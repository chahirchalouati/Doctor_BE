/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Utilities.Projections;

import Doctor.Entities.AppUser;
import Doctor.Entities.PatientDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
public interface PatientProfilePro {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    void setAppUser(AppUser user);

    void setPatientDetails(PatientDetails patientDetails);
}
