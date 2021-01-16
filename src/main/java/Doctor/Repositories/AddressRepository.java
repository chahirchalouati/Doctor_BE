/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Repositories;

import Doctor.Entities.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCityOrProvinceOrAvenue(String city, String province, String avenue);

}
