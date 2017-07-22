package app.in.rest.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName Doctor.java | app.in.rest.entity | Doctor
 * @since Jul 20, 2017 22:26:26 PM
 */



@Entity
@Table(name = "doctor")
//@JsonIgnoreProperties({"patients"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Doctor implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 50, message = "Doctor name length should between 2 to 50 Characters")
    @NotEmpty(message = "Doctor name can not be empty")
    @NotBlank(message = "Doctor name can not be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 2, max = 50, message = "Doctor name length should between 2 to 50 Characters")
    @NotEmpty(message = "Doctor must be under a hospital")
    @NotBlank(message = "Doctor must be under a hospital")
    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    @Range(min = 50, max = 500, message = "Doctor's visit fee should between 50 and 500")
    @Column(name="visit_fee", nullable=false)
    private double visitFee;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonManagedReference
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, updatable = false))
    private Set<Patient> patients = new HashSet<Patient>(0);


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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public double getVisitFee() {
        return visitFee;
    }

    public void setVisitFee(double visitFee) {
        this.visitFee = visitFee;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", visitFee=" + visitFee +
                ", patients=" + patients +
                '}';
    }
}
