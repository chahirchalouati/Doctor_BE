/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Services;

import Doctor.Utilities.Interfaces.AuthenticationService;
import Doctor.Entities.AppRole;
import Doctor.Entities.AppUser;
import Doctor.Exceptions.EntityExceptions.EntityNotFoundException;
import Doctor.Exceptions.EntityExceptions.UserExistException;
import Doctor.Repositories.RoleRepository;
import Doctor.Repositories.UserRepository;
import Doctor.Utilities.ApiResponse.JWTResponse;
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

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    private BCryptPasswordEncoder encoder;

    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

    }

    @Transactional
    @Override
    public ResponseEntity<?> signUp(SignUpRequest request) {

        if (userRepository.existsAppUserByEmail(request.getEmail())) {
            throw new UserExistException("email already exist");
        }
        encoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPassword(encoder.encode(request.getPassword()));
        AppRole findByRole = roleRepository.findByRole(request.getRole());

        if (findByRole == null) {
            throw new EntityNotFoundException("Role Not Found ");
        }
        user.getRoles().add(findByRole);

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        //return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser user = userRepository.findByEmail(request.getEmail().trim().toLowerCase());

        return ResponseEntity.ok(new JWTResponse(jwt, new Date()));

    }

}