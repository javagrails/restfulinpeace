package app.in.rest.service;

import app.in.rest.entity.Doctor;
import app.in.rest.entity.Patient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName DoctorService.java | app.in.rest.service | DoctorService
 * @since Jul 20, 2017 22:51:51 PM
 */

public interface DoctorService {

    // Selections
    Doctor findById(Long id);

    Doctor findByName(String name);

    List<Doctor> findAll();

    boolean isDoctorExist(Doctor doctor);

    // Operations
    @Transactional
    void save(Doctor doctor);

    @Transactional
    void update(Doctor doctor);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteAll();


}
