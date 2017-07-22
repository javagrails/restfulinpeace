package app.in.rest.service;

import app.in.rest.entity.Patient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName PatientService.java | app.in.rest.service | PatientService
 * @since Jul 20, 2017 22:52:52 PM
 */

public interface PatientService {

    // Selections
    Patient findById(Long id);

    Patient findByName(String name);

    Patient findByCity(String city);

    Patient findByPhone(String phone);

    List<Patient> findAll();

    boolean isPatientExist(Patient patient);

    // Operations
    @Transactional
    void save(Patient patient);

    @Transactional
    void update(Patient patient);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteAll();

}
