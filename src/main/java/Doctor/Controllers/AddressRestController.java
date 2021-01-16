/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Entities.Address;
import Doctor.Repositories.AddressRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/addresses")
public class AddressRestController {

    private final AddressRepository addressRepository;

    public AddressRestController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Get Addresses by City or province or avenue
     *
     * @param param
     * @return List Of Address
     */
    @GetMapping(value = "/{param}")
    public ResponseEntity<List<Address>> list(@PathVariable String param) {
        return new ResponseEntity<>(
                addressRepository.findByCityOrProvinceOrAvenue(param, param, param),
                HttpStatus.OK);
    }
//
//    @GetMapping("/{id}")
//    public Object get(@PathVariable String id) {
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> post(@RequestBody Object input) {
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }

}
