/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Services;

import Doctor.Entities.AppUser;
import Doctor.Repositories.RoleRepository;
import Doctor.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity create(AppUser user, String role) {
        user.getRoles().add(roleRepository.findByRole(role));
        AppUser save = userRepository.save(user);
        return new ResponseEntity(save, HttpStatus.CREATED);
    }

    public ResponseEntity delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResponseEntity update(AppUser user, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

    }

}
