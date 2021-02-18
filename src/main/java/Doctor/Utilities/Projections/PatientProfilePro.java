/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Utilities.Projections;

import Doctor.Entities.AppUser;
import Doctor.Entities.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientProfilePro {

    AppUser user;
    PatientDetails patientDetails;
}
