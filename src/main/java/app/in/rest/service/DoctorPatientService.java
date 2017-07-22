package app.in.rest.service;

import app.in.rest.entity.Doctor;
import app.in.rest.entity.Patient;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author M.M. SALEH (Salman)
 * @designation Senior Software Engineer
 * @github https://github.com/javagrails
 * @fileName DoctorPatientService.java | app.in.rest.service | DoctorPatientService
 * @since Jul 22, 2017 16:13:13 PM
 */

public interface DoctorPatientService {

    Doctor getDoctor(Long doctorId, boolean isBuild);

    void saveDoctor(Doctor doctor);

    boolean isDoctorExist(Long doctorId);

    boolean isPatientExist(Long doctorId);

    @Transactional
    void savePatientWithDoctor(Long doctorId, Patient patient);


    // Utility methods
    Patient buildPatientFromDbPatient(Patient patientIn);

    Patient buildPatient(String name, String city, String phone);

    Doctor buildDoctorFromDbDoctor(Doctor doctorIn);

    Doctor buildDoctor(String name, String hospitalName, Double visitFee);

}
