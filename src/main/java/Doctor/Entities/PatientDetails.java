/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Table(name = "PatientDetails")
public class PatientDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    @Column(nullable = false)
    private String avatarUrl;

    private Double height;

    private Double weight;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date birthdate;

    @NotNull(message = "")
    @Column(nullable = false)
    private Character gender;

    @NotBlank(message = "")
    @Column(nullable = false)
    private String taxCode;

    @NotBlank(message = "")
    @Column(nullable = false)
    private String numberPhone;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonProperty(required = false)
    @OneToOne
    private AppUser patient;

    @ManyToOne(cascade = CascadeType.ALL)
    private BloodType bloodType;

    @JsonProperty(required = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistory = new ArrayList<>();  //medical history Like his permenent desease

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
}
