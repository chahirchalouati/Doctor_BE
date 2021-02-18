/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Services;

import Doctor.Entities.File;
import Doctor.Entities.FileType;
import Doctor.Exceptions.StorageException;
import Doctor.Repositories.FileRepository;
import Doctor.Repositories.FileTypeRepository;
import Doctor.Repositories.UserRepository;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@Slf4j
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileTypeRepository fileTypeRepository;

    private final Path fileStorageLocation;
    private final String path = "./Doctor/Files";
    private String newFileName;
    private boolean isStored;
    private File file;

    public FileService() {
        this.fileStorageLocation = createPath(path);
        createDirectory(fileStorageLocation);
        this.isStored = false;
        this.file = new File();

    }

    public final Path createPath(String path) {
        return Paths
                .get(path)
                .toAbsolutePath()
                .normalize();
    }

    public final boolean createDirectory(Path directory) {

        try {

            Files.createDirectories(directory);

            return true;

        } catch (IOException ex) {
            throw new StorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Transactional
    public File storeFile(MultipartFile file, String username) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new StorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            this.newFileName = UUID.randomUUID() + "." + fileName.split("\\.")[1];
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation
                    .resolve(username.toLowerCase().trim() + "/" + newFileName);

            if (!Files.exists(targetLocation)) {
                createDirectory(targetLocation);
            }
            this.file = StoreFileInDb(file, targetLocation, username);
            if (this.isStored) {
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            return this.file;

        } catch (IOException ex) {

            throw new StorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Transactional
    private File StoreFileInDb(MultipartFile file, Path targetLocation, String username) {

        if (fileTypeRepository.findByType(file.getContentType()) == null) {// check if filetype exist
            fileTypeRepository.save(new FileType(file.getContentType()));//store new filetype
        }

        if (!userRepository.existsAppUserByEmail(username)) {
            throw new UsernameNotFoundException("User  Not Found  : " + username);
        }

        File f;
        f = new File(
                this.newFileName,// change here for name
                "/files/" + username.concat("/").concat(this.newFileName),
                targetLocation.toString(),
                file.getSize(),
                fileTypeRepository.findByType(file.getContentType()),
                userRepository.findByEmail(username));

        fileRepository.save(f);//save file
        this.isStored = true;
        return f;
    }

    public ResponseEntity<?> downloadFile(String fileName, String username, HttpServletRequest request)
            throws IOException {
        try {
            File f = fileRepository.findByNameAndUserName(fileName, username);

            if (f == null) {
                throw new StorageException("File Not Found : " + fileName);
            }
            // create object of Path
            Path pathToResource = Paths.get(f.getPath()).normalize().toAbsolutePath();

            Resource resource = new UrlResource(pathToResource.toUri());

            if (resource.exists()) {
                // Try to determine file's content type
                String contentType = null;
                try {
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                } catch (IOException ex) {
                    log.info("Could not determine file type.");
                }

                // Fallback to the default content type if type could not be determined
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePrivate().proxyRevalidate())
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getName() + "\"")
                        .body(resource);

            } else {
                throw new StorageException("File not found " + fileName);
            }

        } catch (MalformedURLException ex) {
            throw new StorageException("File not found " + fileName, ex);
        }
    }

}
