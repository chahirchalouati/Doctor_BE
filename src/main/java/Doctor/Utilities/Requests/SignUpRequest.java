/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Utilities.Requests;

import Doctor.Exceptions.EntityExceptions.PasswordInvalidException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;
    private String role;

    public String getPassword() {
        boolean matches = password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        if (matches) {
            return password;
        }
        throw new PasswordInvalidException("\"Password must contain at least one digit [0-9].\"\n"
                + "            + \"Password must contain at least one lowercase Latin character [a-z].\"\n"
                + "            + \"Password must contain at least one uppercase Latin character [A-Z].\"\n"
                + "            + \"Password must contain at least one special character like ! @ # & ( ).\"\n"
                + "            + \"Password must contain a length of at least 8 characters and a maximum of 20 characters\"");
    }

}
