/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Repositories.BloodTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/bloodTypes")
public class BloodTypeRestController {

    private final BloodTypeRepository bloodTypeRepository;

    public BloodTypeRestController(BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
    }

    /**
     *
     * @return List Of bloodType
     */
    @GetMapping()
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(bloodTypeRepository.findAll());
    }

}
