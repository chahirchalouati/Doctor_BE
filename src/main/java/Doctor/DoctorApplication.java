package Doctor;

import Doctor.Entities.AppRole;
import Doctor.Repositories.RoleRepository;
import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {

        return args -> {
            roleRepository.deleteAll();
            roleRepository.saveAll(
                    List.of(
                            new AppRole(null, "USER", new Date()),
                            new AppRole(null, "ADMIN", new Date()),
                            new AppRole(null, "DOCTOR", new Date())
                    )
            );

        };
    }

}
