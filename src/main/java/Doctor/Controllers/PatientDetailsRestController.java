/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Services.PatientDetailsService;
import Doctor.Utilities.Projections.PatientDetailsPro;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/patientDetails")
@AllArgsConstructor
public class PatientDetailsRestController {

    private PatientDetailsService patientDetailsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> post(@ModelAttribute PatientDetailsPro patientDetails,
            Principal principal) {

        System.out.println("\n\n\n\n\n" + patientDetails.toString() + "\n\n\n\n\n");
        return patientDetailsService.create(patientDetails, principal);
    }

}
