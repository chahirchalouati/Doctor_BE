/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Chahir Chalouati
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "Password must contain at least one digit [0-9]."
            + "Password must contain at least one lowercase Latin character [a-z]."
            + "Password must contain at least one uppercase Latin character [A-Z]."
            + "Password must contain at least one special character like ! @ # & ( )."
            + "Password must contain a length of at least 8 characters and a maximum of 20 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
