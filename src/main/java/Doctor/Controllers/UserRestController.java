/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Entities.AppUser;
import Doctor.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    /**
     * Create new user
     *
     * @param user
     * @return AppUser
     */
    @PostMapping
    public ResponseEntity<?> post(@RequestBody AppUser user) {
        return userService.create(user, "USER");
    }

    /**
     * Get User profile
     *
     * @param user
     * @return AppUser
     */
    @GetMapping(value = "/profile")
    public ResponseEntity<?> getUserProfile(@RequestParam(name = "email") String email) {
        return userService.getUserProfile(email);
    }

}
