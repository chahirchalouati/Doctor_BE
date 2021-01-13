/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor;

import Doctor.Repositories.RoleRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;

/**
 *
 * @author Chahir Chalouati
 */
public class DoctorApplicationTest {

    public DoctorApplicationTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class DoctorApplication.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        DoctorApplication.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commandLineRunner method, of class DoctorApplication.
     */
    @Test
    public void testCommandLineRunner() {
        System.out.println("commandLineRunner");
        RoleRepository roleRepository = null;
        DoctorApplication instance = new DoctorApplication();
        CommandLineRunner expResult = null;
        CommandLineRunner result = instance.commandLineRunner(roleRepository);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
