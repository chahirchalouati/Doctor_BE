package Doctor.Controllers;

import Doctor.Services.AuthenticationServiceImpl;
import Doctor.Utilities.Requests.SignInRequest;
import Doctor.Utilities.Requests.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/auths")
@Slf4j
public class AuthRestController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    /**
     * Inject Authentication Service
     *
     * @param authenticationServiceImpl
     */
    public AuthRestController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    /**
     * User Sign-In
     *
     * @param request
     * @return JWTResponse with generated Token
     */
    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        return authenticationServiceImpl.signIn(request);
    }

    /**
     * User Sign-Up
     *
     * @param request
     * @return new registered User
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        return authenticationServiceImpl.signUp(request);
    }
}
