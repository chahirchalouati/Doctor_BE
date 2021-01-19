/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Entities.PatientDetails;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/patientDetails")
public class PatientDetailsRestController {

    @PostMapping
    public ResponseEntity<?> post(@RequestBody PatientDetails patientDetails, Principal principal) {
        System.out.println(patientDetails.toString());
        return ResponseEntity.accepted().build();
    }

}
