/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    private String firstName;

    @NotBlank(message = "")
    private String lastName;

    @Email(message = "Invalid E-mail address")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit [0-9]"
            + "Password must contain at least one lowercase Latin character [a-z]."
            + "Password must contain at least one uppercase Latin character [A-Z]."
            + "Password must contain at least one special character like ! @ # & ( )."
            + "Password must contain a length of at least 8 characters and a maximum of 20 characters")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AppRole> roles = new ArrayList<>();

    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isAccountNonLocked = true;
    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isAccountNonExpired = true;
    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isCredentialsNonExpired = true;
    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isEnabled = true;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;

}
