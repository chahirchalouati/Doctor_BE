/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Repositories;

import Doctor.Entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Chahir Chalouati
 */
public interface FileRepository extends JpaRepository<File, Long> {

    public File findByNameAndUserName(@Param("name") String fileName, @Param("username") String username);
//
//    public File findByNameAndUserName(String fileName, String username);

}
