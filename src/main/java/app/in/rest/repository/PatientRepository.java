package app.in.rest.repository;

import app.in.rest.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName PatientRepository.java | app.in.rest.repository | PatientRepository
 * @since Jul 20, 2017 22:36:36 PM
 */

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE LOWER(p.name) = LOWER(:name)")
    public Patient findByName(@Param("name") String name);

    @Query("SELECT p FROM Patient p WHERE LOWER(p.phone) = LOWER(:phone)")
    public Patient findByPhone(@Param("phone") String phone);

    @Query("SELECT p FROM Patient p WHERE LOWER(p.city) = LOWER(:city)")
    public Patient findByCity(@Param("city") String city);

    @Query("SELECT p FROM Patient p WHERE p.city = :city")
    public List<Patient> findAllByCity(@Param("city") String city);

}
