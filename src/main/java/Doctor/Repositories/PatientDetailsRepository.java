/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Repositories;

import Doctor.Entities.PatientDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Long> {

    public List<PatientDetails> findByPatient_id(Long id);

    public List<PatientDetails> findByPatient_idOrderByCreatedAtDesc(Long id);

}
