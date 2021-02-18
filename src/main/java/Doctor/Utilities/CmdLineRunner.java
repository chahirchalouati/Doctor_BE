/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Utilities;

import Doctor.Entities.AppRole;
import Doctor.Entities.BloodType;
import Doctor.Repositories.BloodTypeRepository;
import Doctor.Repositories.RoleRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
public class CmdLineRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final BloodTypeRepository bloodTypeRepository;

    public CmdLineRunner(RoleRepository roleRepository, BloodTypeRepository bloodTypeRepository) {
        this.roleRepository = roleRepository;
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        if (roleRepository.count() <= 0 && bloodTypeRepository.count() <= 0) {
            //add roles
            List<AppRole> collectRoles = Arrays.asList(new String[]{"USER", "ADMIN", "Doctor"})
                    .stream()
                    .map(r -> new AppRole(null, r, new Date()))
                    .collect(Collectors.toList());

            roleRepository.saveAll(collectRoles);
            //add bloodType
            List<BloodType> collectBloodTypes = Arrays.asList(new String[]{"A+", "A-", "B+", "B-", "O+", "AB+", "AB-"})
                    .stream()
                    .map(r -> new BloodType(null, r, new Date()))
                    .collect(Collectors.toList());

            bloodTypeRepository.saveAll(collectBloodTypes);
        }

    }

}
