/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Controllers;

import Doctor.Repositories.FileRepository;
import Doctor.Services.FileService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    FileService fileStorageService;
    @Autowired
    FileRepository fileRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(fileRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userName}/{fileName}")
    public ResponseEntity<?> get(
            @PathVariable String fileName,
            @PathVariable String userName,
            HttpServletRequest request) throws IOException {

        return fileStorageService.downloadFile(fileName, userName, request);
    }

}
