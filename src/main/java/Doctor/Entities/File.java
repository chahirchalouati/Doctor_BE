/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FILES")

@NamedQueries({
    @NamedQuery(
            name = "File.findByNameAndUserName",
            query = "SELECT f FROM File f WHERE f.name = :name AND f.user.email = :username"
    )

})
public class File implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name can't be blank")
    @Column(unique = true)
    private String name;
    @NotBlank(message = "URI can't be blank")
    @Column(unique = true)
    private String URL;

    @JsonIgnore
    @NotBlank(message = "PATH can't be blank")
    @Column(unique = true)
    private String path;

    private Long size;

    @ManyToOne
    private FileType fileType;
    @JsonBackReference
    @ManyToOne
    private AppUser user;

    public File(String name, String URL, String path, Long size, FileType fileType, AppUser user) {
        this.name = name;
        this.URL = URL;
        this.path = path;
        this.size = size;
        this.fileType = fileType;
        this.user = user;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

}
