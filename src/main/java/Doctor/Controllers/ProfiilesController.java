/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Services.ProfileService;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/profiles")
@AllArgsConstructor
public class ProfiilesController {

    private final ProfileService profileService;

    @GetMapping(value = "/patient")
    public ResponseEntity<?> getPatient(@RequestParam(value = "email", required = true) String email, Principal principal) {
        return profileService.findPatientProfile(principal, email);
    }

    @GetMapping(value = "/Doctor")
    public ResponseEntity<?> getDoctor(@RequestParam(value = "email", required = true) String email, Principal principal) {
        return profileService.findDoctorProfile(principal, email);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<?> getAdmin(@RequestParam(value = "email", required = true) String email, Principal principal) {
        return profileService.findAdminProfile(principal, email);
    }
}
