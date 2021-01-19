/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Services;

import Doctor.Entities.AppRole;
import Doctor.Entities.AppUser;
import Doctor.Repositories.RoleRepository;
import Doctor.Repositories.UserRepository;
import Doctor.Utilities.ApiResponse.JWTResponse;
import Doctor.Utilities.Interfaces.AuthenticationService;
import Doctor.Utilities.JwtUtils;
import Doctor.Utilities.Requests.SignInRequest;
import Doctor.Utilities.Requests.SignUpRequest;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chahir Chalouati
 */
@Service

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private BCryptPasswordEncoder encoder;

    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
            AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

    }

    @Override
    @Transactional
    public ResponseEntity<?> signUp(SignUpRequest request) {

        encoder = new BCryptPasswordEncoder();
        //find role
        AppRole findByRole = roleRepository.findByRole(request.getRole());

        //create user
        AppUser user = new AppUser();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(request.getEmail().trim());
        user.setPassword(encoder.encode(request.getPassword()));

        user.getRoles().add(findByRole);
        AppUser save = userRepository.saveAndFlush(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser user = userRepository.findByEmail(request.getEmail().trim().toLowerCase());

        return ResponseEntity.ok(new JWTResponse(jwt, new Date()));

    }

}
