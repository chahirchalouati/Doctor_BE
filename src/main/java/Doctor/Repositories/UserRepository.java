/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Repositories;

import Doctor.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);

    public boolean existsAppUserByEmail(String email);

}
