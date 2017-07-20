package app.in.rest.repository;

import app.in.rest.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName DoctorRepository.java | app.in.rest.repository | DoctorRepository
 * @since Jul 20, 2017 22:37:37 PM
 */

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE LOWER(d.name) = LOWER(:name)")
    public Doctor findByName(@Param("name") String name);

    @Query("SELECT d FROM Doctor d WHERE d.visitFee <= :visitFee")
    public List<Doctor> findAllByVisitFeeIsLessThanEqual(@Param("visitFee") Double visitFee);

    @Query("SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.id = :id")
    public Doctor findDoctorWithPatientsByDoctorId(@Param("id") Long id);

}