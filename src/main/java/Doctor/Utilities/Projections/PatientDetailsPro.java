/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Utilities.Projections;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class PatientDetailsPro {

    private Integer CAP;

    private String province;

    private String avenue;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private Integer building;

    private String city;

    private MultipartFile file;

    private Character gender;

    private Double height;

    private Double weight;

    private String bloodType;

    private String taxCode;
    private String numberPhone;
}
