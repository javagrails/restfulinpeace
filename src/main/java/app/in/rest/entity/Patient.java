package app.in.rest.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName Patient.java | app.in.rest.entity | Patient
 * @since Jul 20, 2017 22:28:28 PM
 */


@Entity
@Table(name = "patient")
public class Patient implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 50, message = "Patient name length should between 2 to 50 Characters")
    @NotEmpty(message = "Patient name can not be empty")
    @NotBlank(message = "Patient name can not be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 2, max = 11, message = "Patient phone length should between 2 to 11 Characters")
    @NotEmpty(message = "Patient phone can not be empty")
    @NotBlank(message = "Patient phone can not be blank")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Size(min = 2, max = 30, message = "City name length should between 2 to 30 Characters")
    @Column(name = "city", nullable = true)
    private String city;

    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<Doctor>(0);;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
